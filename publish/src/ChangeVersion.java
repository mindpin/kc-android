import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ChangeVersion {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws XPathExpressionException 
	 * @throws TransformerException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException, InterruptedException {
		String new_version = "";
		String is_milestone_str = "";
		boolean is_milestone = false;
		
		
		while(version_format_is_error(new_version)){
			System.out.print("input new version(x.x.x):");
			new_version = new Scanner(System.in).next();
		}
		
		while(is_milestone_format_is_error(is_milestone_str)){
			System.out.print("input is_milestone(Y/N):");
			is_milestone_str = new Scanner(System.in).next();
		}
		is_milestone = get_is_milestone(is_milestone_str);
		
		String current_version = get_current_version();
		if(!first_verion_more_than_second(new_version, current_version)){
			System.out.println("new version must more than " + current_version);
			System.exit(1);
		}
		
		update_version(new_version);
		
		int result_code = run_cmd("mvn clean install -Prelease");
		if(result_code != 0){
			run_cmd("git checkout .");
			System.out.println("create apk failed, git checkout .");
			System.exit(1);
		}
		
		
//		File apk_file = new File(get_project_file(),"app/target/app.apk");
		
		run_cmd("git add -A");
		run_cmd("git commit -m " + "\"" + "release " + new_version + " version\"");
		run_cmd("git tag " + new_version);
		System.out.println("publish " + new_version);
	}
	
	private static int run_cmd(String cmd){
		try{
			Runtime run = Runtime.getRuntime();
			
			if(System.getProperties().getProperty("os.name").matches("Windows.*")){
				cmd = "cmd.exe /c " + cmd;
			}
			Process p = run.exec(cmd, null, get_project_file());
			print_cmd_output(p);
			return p.waitFor();
		} catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}
	
	private static void print_cmd_output(Process p){
		try{
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));  
        String line = "";  
        while ((line = input.readLine()) != null) {  
        	System.out.println(line);  
        }  
        input.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	private static boolean version_format_is_error(String version){
		Pattern pattern = Pattern.compile("^[0-9]+[\\.][0-9]+[\\.][0-9]+$");
		Matcher matcher = pattern.matcher(version);
		return !matcher.matches();
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
	
	private static File get_version_xml_file(){
//		version_xml_file_path "app/res/values/versions.xml"
		File project_file = get_project_file();
		String path = project_file.getAbsoluteFile() + File.separator + 
					  "app" + File.separator +
					  "res" + File.separator +
					  "values" + File.separator +
					  "versions.xml";
		return new File(path);
	}
	
	private static File get_project_file(){
		File f = new File(ChangeVersion.class.getResource("/").getPath());
		File project_file = f.getParentFile().getParentFile();
		return project_file;
	}

	private static String get_current_version(){
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(get_version_xml_file());
			
			XPathFactory f = XPathFactory.newInstance();
			XPath path = f.newXPath();
			NodeList node_list = (NodeList)path.evaluate("resources/string", document, XPathConstants.NODESET);
			
			for(int i=0; i< node_list.getLength(); i++){
				Node node = node_list.item(i);
				Node attr = node.getAttributes().getNamedItem("name");
				if(attr.getNodeValue().equals("versionName")){
					return node.getTextContent();
				}
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	private static void update_version(String new_version) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(get_version_xml_file());
		
		update_version_name(document,new_version);
		update_version_code(document);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult streamResult =  new StreamResult(get_version_xml_file());
		transformer.transform(source, streamResult);
	}
	
	
	private static boolean first_verion_more_than_second(String new_version, String old_version){
		String[] new_arr = new_version.split("\\.");
		String[] old_arr = old_version.split("\\.");
		
		
		if(Integer.parseInt(new_arr[0]) > Integer.parseInt(old_arr[0])){
			return true;
		}
		if(Integer.parseInt(new_arr[0]) < Integer.parseInt(old_arr[0])){
			return false;
		}
		
		
		if(Integer.parseInt(new_arr[1]) > Integer.parseInt(old_arr[1])){
			return true;
		}
		if(Integer.parseInt(new_arr[1]) < Integer.parseInt(old_arr[1])){
			return false;
		}
		
		
		if(Integer.parseInt(new_arr[2]) > Integer.parseInt(old_arr[2])){
			return true;
		}
		return false;
	}



	private static void update_version_code(Document document) throws XPathExpressionException {
		XPathFactory f = XPathFactory.newInstance();
		XPath path = f.newXPath();
		Node node = (Node) path.evaluate("resources/integer[@name='versionCode']", document, XPathConstants.NODE);
		String old_code_str = node.getTextContent();
		int new_code = Integer.parseInt(old_code_str) + 1;
		node.setTextContent(new_code+"");
	}



	private static void update_version_name(Document document,
			String new_version) throws XPathExpressionException {
		
		XPathFactory f = XPathFactory.newInstance();
		XPath path = f.newXPath();
		Node node = (Node)path.evaluate("resources/string[@name='versionName']", document, XPathConstants.NODE);
		node.setTextContent(new_version);
	}
}
