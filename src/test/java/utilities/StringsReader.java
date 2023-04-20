package utilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

public class StringsReader {

    HashMap<String, String> strings = new HashMap<>();
    InputStream stringInputStream;
    StringsReader stringsReader;
    InputStream inputStream;

    public HashMap<String, String> parseStringXML(InputStream file) throws Exception {
        HashMap<String, String> stringMap = new HashMap<String, String>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("string");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
            }
        }
        return stringMap;
    }

    public String readStringsXML(String XMLStringName) throws Exception {
        String xmlFilePath = "strings" + File.separator + "strings.xml";
        stringInputStream = getClass().getClassLoader().getResourceAsStream(xmlFilePath);
        stringsReader = new StringsReader();
        strings = stringsReader.parseStringXML(stringInputStream);

        if (inputStream != null) {
            inputStream.close();
        }
        if (stringInputStream != null) {
            stringInputStream.close();
        }
        return strings.get(XMLStringName);
    }
}
