package parser;

import model.apbuilding.AirPlaneModel;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Denis on 12/21/2014.
 * Lets choose parsers to parse XML
 */
public class Parser {
    public static ArrayList<AirPlaneModel> parse(char choice){
        ArrayList<AirPlaneModel> planeArray = null;
        try {

            /*TransformerFactory tFactory = TransformerFactory.newInstance();

            Source xslDoc = new StreamSource("AirPlaneModel.xsl");
            Source xmlDoc = new StreamSource("AirPlaneModel.xml");

            String outputFileName = "plane.html";

            OutputStream htmlFile = new FileOutputStream(outputFileName);
            Transformer trasform = tFactory.newTransformer(xslDoc);
            trasform.transform(xmlDoc, new StreamResult(htmlFile)); */
            switch (choice) {
                case 'd': {
                    ParseDOM parseDom = new ParseDOM();
                    parseDom.initialize("AirPlaneModel.xml");
                    planeArray = parseDom.getArrayFromXML();
                    break;
                }
                case 's': {
                    ParseSAX parseSAX = new ParseSAX();
                    planeArray = parseSAX.getArrayFromXML();
                    break;
                }
                case 't': {
                    ParseStAX parseStAX = new ParseStAX();
                    planeArray = parseStAX.getArrayFromXML();
                    break;
                }
                default:
                    return null;
            }

            System.out.println("**parsing completed**");
            for (int i = 0; i < planeArray.size(); i++)
                System.out.println(planeArray.get(i).toString());

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return planeArray;
    }

}
