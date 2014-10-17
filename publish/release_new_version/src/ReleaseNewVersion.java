import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class ReleaseNewVersion {

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
		
		while(version_format_is_error(new_version)){
			System.out.print("input new version(x.x.x):");
			new_version = new Scanner(System.in).next();
		}
		
		String current_version = Util.get_current_version();
		if(!Util.first_verion_more_than_second(new_version, current_version)){
			System.out.println("new version must more than " + current_version);
			System.exit(1);
		}
		
		update_version(new_version);
		
		int result_code = Util.run_cmd("mvn clean install");
		if(result_code != 0){
			Util.run_cmd("git checkout .");
			System.out.println("create apk failed, git checkout .");
			System.exit(1);
		}
		
		Util.run_cmd("git add -A");
		Util.run_cmd("git commit -m " + "\"" + "release " + new_version + " version\"");
		Util.run_cmd("git tag " + new_version);
		System.out.println("publish " + new_version);
	}
	
	private static boolean version_format_is_error(String version){
		Pattern pattern = Pattern.compile("^[0-9]+[\\.][0-9]+[\\.][0-9]+$");
		Matcher matcher = pattern.matcher(version);
		return !matcher.matches();
	}
	
	private static void update_version(String new_version) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(Util.get_version_xml_file());
		
		update_version_name(document,new_version);
		update_version_code(document);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult streamResult =  new StreamResult(Util.get_version_xml_file());
		transformer.transform(source, streamResult);
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
