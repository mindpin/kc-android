import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Util {

	public static int run_cmd(String cmd){
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
	
	
	public static void print_cmd_output(Process p){
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
	
	
	public static File get_project_file(){
		File f = new File(Util.class.getResource("/").getPath());
		File project_file = f.getParentFile().getParentFile().getParentFile();
		return project_file;
	}

	public static String get_current_version(){
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
	
	
	public static File get_version_xml_file(){
//		version_xml_file_path "app/res/values/versions.xml"
		File project_file = get_project_file();
		String path = project_file.getAbsoluteFile() + File.separator + 
					  "app" + File.separator +
					  "res" + File.separator +
					  "values" + File.separator +
					  "versions.xml";
		return new File(path);
	}
	
	public static boolean first_verion_more_than_second(String new_version, String old_version){
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
	
}
