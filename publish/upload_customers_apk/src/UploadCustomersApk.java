import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class UploadCustomersApk {
	public static final String SITE = "http://kc-android-publish-service.4ye.me";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String is_milestone_str = "";
		boolean is_milestone = false;
		
		while(is_milestone_format_is_error(is_milestone_str)){
			System.out.print("input is_milestone(Y/N):");
			is_milestone_str = new Scanner(System.in).next();
		}
		is_milestone = get_is_milestone(is_milestone_str);
		
		get_customers_json_str();
		
        Type collectionType = new TypeToken<List<Customer>>(){}.getType();
        Gson gson = new Gson();
        List<Customer> customer_list = gson.fromJson(get_customers_json_str(), collectionType);
		
        for(Customer c : customer_list){
        	process_customer(c, is_milestone);
        }
        
        System.out.println("upload customers apk success!");
	}
	
	private static void process_customer(Customer c, boolean is_milestone) {
		System.out.println("处理 customer " + c.name);
		
		// newest_version
		String current_version = Util.get_current_version();
		if(Util.first_verion_more_than_second(c.newest_version, current_version)){
			System.out.println(c.name + "的版本已经是 " + c.newest_version + ",大于准备上传的版本 " + current_version + ", 所以 pass");
			return;
		}
		
		// http_site.xml
		change_http_site_xml_file(c.http_site);
		// app.keystore
		download_keystore_file(c.keystore_file_url);
		// env-release.properties
		change_env_properties_file(c);
		// mvn install
		build_apk();
		// upload apk
		upload_customer_apk(c, is_milestone, current_version);
	}
	
	
	
	private static void upload_customer_apk(Customer c, boolean is_milestone, String current_version) {
		HttpRequest request = HttpRequest.post(SITE + "/upload");
		File file = new File(Util.get_project_file(), "app/target/app.apk");
		
		request.part("customer_name", c.name);
		request.part("version", current_version);
		request.part("is_milestone", is_milestone+"");
		request.part("package", "app.apk", file);
		if(!request.ok()){
			System.exit(1);
		}
	}

	private static void build_apk() {
		int result_code = Util.run_cmd("mvn clean install -Prelease");
		if(result_code != 0){
			Util.run_cmd("git checkout .");
			System.out.println("create apk failed, git checkout .");
			System.exit(1);
		}
	}

	private static void change_env_properties_file(Customer c) {
		File file = new File(Util.get_project_file(), "app/env-release.properties");
		write_properties_file_attr(file, "keystore.storepass", c.storepass);
		write_properties_file_attr(file, "keystore.keypass", c.keypass);
	}
	
	private static void write_properties_file_attr(File properties_file, String name, String value){
        Properties prop = new Properties();  
        InputStream fis = null;  
        OutputStream fos = null;  
        try {  
            fis = new FileInputStream(properties_file);  
            prop.load(fis);
            
            fos = new FileOutputStream(properties_file);  
            prop.setProperty(name, value);
            prop.store(fos, null);
            fis.close();
            fos.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }  
        finally{  
            try {  
                fos.close();  
                fis.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }
        }  
	}

	private static void download_keystore_file(String keystore_file_url) {
		HttpRequest request = HttpRequest.get(SITE + keystore_file_url);
		File new_file = new File(Util.get_project_file(), "app/app.keystore");
		request.receive(new_file);
	}

	private static void change_http_site_xml_file(String http_site){
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(get_http_site_file());
			
			XPathFactory f = XPathFactory.newInstance();
			XPath path = f.newXPath();
			Node node = (Node) path.evaluate("resources/string[@name='http_site']", document, XPathConstants.NODE);
			node.setTextContent(http_site);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult streamResult =  new StreamResult(get_http_site_file());
			transformer.transform(source, streamResult);
		}catch(Exception ex){
			ex.printStackTrace();
			System.exit(1);
		}
	}
	
	public static File get_http_site_file(){
//		version_xml_file_path "app/res/values/http_site.xml"
		File project_file = Util.get_project_file();
		String path = project_file.getAbsoluteFile() + File.separator + 
					  "app" + File.separator +
					  "res" + File.separator +
					  "values" + File.separator +
					  "http_site.xml";
		return new File(path);
	}

	private static String get_customers_json_str(){
		HttpRequest request = HttpRequest.get("http://192.168.1.38:3000/customers");
		if(request.ok()){
			return request.body();
		}else{
			System.out.println("获取 customers 列表失败，上传 apk 失败");
			System.exit(1);
			return "";
		}
	}
	
	private static boolean is_milestone_format_is_error(String is_milestone_str){
		List<String> option_list = Arrays.asList("Y","y","N","n");
		return option_list.indexOf(is_milestone_str) == -1;
	}
	
	private static boolean get_is_milestone(String is_milestone_str){
		if(is_milestone_str.equals("Y") || is_milestone_str.equals("y")){
			return true;
		}
		return false;
	}

}
