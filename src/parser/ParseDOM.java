package parser;

import model.apbuilding.AirPlaneModel;
import model.apbuilding.AnyBuilder;
import model.details.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Denis on 12/21/2014.
 * Implements functional of DOM parser's
 */
public class ParseDOM {
    protected static enum toyType{CAR, DOLL};

    protected Document doc;
    public Document getDocument(){
        return doc;
    }
    public void initialize(String xmlPath) {
        try
        {
            File xmlFile = new File(xmlPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(xmlFile);
        }
        catch (SAXException e)
        {
            e.printStackTrace();
            return ;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return ;
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
            return ;
        }
    }
    public ArrayList<AirPlaneModel> getArrayFromXML(){
        NodeList nodeList = doc.getElementsByTagName("new_plane");
        ArrayList<AirPlaneModel> planeArray = new ArrayList<AirPlaneModel>();
        String [] value = {"Fuselage", "LandGear", "Avionic", "Engine"};
        Fuselage fuselage = null;
        LandGear landgear = null;
        Avionic avionic = null;
        Engine engine = null;
        String name;
        double flightRange;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Node detailOfPlane;
                name = element.getElementsByTagName("name").item(0).getTextContent();
                flightRange = Double.parseDouble(element.getElementsByTagName("flightRange").item(0).getTextContent());
                if ((detailOfPlane = element.getElementsByTagName(value[0]).item(0)).getNodeType() == Node.ELEMENT_NODE) {
                    Element detail = (Element) detailOfPlane;

                    String nameFus, info;
                    double cost, weight, length, fuelVolume, load;
                    int capacity;
                    nameFus = detail.getElementsByTagName("name").item(0).getTextContent();
                    info = detail.getElementsByTagName("info").item(0).getTextContent();
                    capacity = Integer.parseInt(detail.getElementsByTagName("capacityPass").item(0).getTextContent());
                    cost = Double.parseDouble(detail.getElementsByTagName("cost").item(0).getTextContent());
                    weight = Double.parseDouble(detail.getElementsByTagName("weight").item(0).getTextContent());
                    length = Double.parseDouble(detail.getElementsByTagName("length").item(0).getTextContent());
                    fuelVolume = Double.parseDouble(detail.getElementsByTagName("fuelVolume").item(0).getTextContent());
                    load = Double.parseDouble(detail.getElementsByTagName("load").item(0).getTextContent());
                    switch (Integer.parseInt(detail.getElementsByTagName("type").item(0).getTextContent())) {
                        case AirPlaneModel.PASS: {
                            boolean bar = Boolean.valueOf(detail.getElementsByTagName("bar").item(0).getTextContent());
                            fuselage = new PassengerFesulage(cost, nameFus, info, weight, length, fuelVolume,
                                    bar, capacity, load);
                            break;
                        }
                        case AirPlaneModel.FREIGHT: {
                            fuselage = new FreightFesulage(cost, nameFus, info, weight, length, fuelVolume, load, capacity);
                            break;
                        }
                        case AirPlaneModel.MIL: {
                            int rock, bomb;
                            rock = Integer.parseInt(detail.getElementsByTagName("rockets").item(0).getTextContent());
                            bomb = Integer.parseInt(detail.getElementsByTagName("bombs").item(0).getTextContent());
                            fuselage = new MilitaryFuselage(cost, nameFus, info, weight, length, fuelVolume,
                                    rock, bomb, capacity, load);
                            break;
                        }
                    }
                }
                if ((detailOfPlane = element.getElementsByTagName(value[1]).item(0)).getNodeType() == Node.ELEMENT_NODE) {
                    Element detail = (Element) detailOfPlane;
                    String nameGear, info;
                    double cost, weight;
                    nameGear = detail.getElementsByTagName("name").item(0).getTextContent();
                    info = detail.getElementsByTagName("info").item(0).getTextContent();
                    cost = Double.parseDouble(detail.getElementsByTagName("cost").item(0).getTextContent());
                    weight = Double.parseDouble(detail.getElementsByTagName("weight").item(0).getTextContent());
                    landgear = new LandGear(cost, nameGear, info, weight);
                }
                if ((detailOfPlane = element.getElementsByTagName(value[2]).item(0)).getNodeType() == Node.ELEMENT_NODE) {
                    Element detail = (Element) detailOfPlane;
                    String nameAvionic, info;
                    double cost, weight;
                    nameAvionic = detail.getElementsByTagName("name").item(0).getTextContent();
                    info = detail.getElementsByTagName("info").item(0).getTextContent();
                    cost = Double.parseDouble(detail.getElementsByTagName("cost").item(0).getTextContent());
                    weight = Double.parseDouble(detail.getElementsByTagName("weight").item(0).getTextContent());
                    avionic = new Avionic(cost, nameAvionic, info, weight);
                }
                if ((detailOfPlane = element.getElementsByTagName(value[3]).item(0)).getNodeType() == Node.ELEMENT_NODE) {
                    Element detail = (Element) detailOfPlane;
                    String nameEng, info, typeEng;
                    double cost, weight, power;
                    nameEng = detail.getElementsByTagName("name").item(0).getTextContent();
                    info = detail.getElementsByTagName("info").item(0).getTextContent();
                    typeEng = detail.getElementsByTagName("type").item(0).getTextContent();
                    cost = Double.parseDouble(detail.getElementsByTagName("cost").item(0).getTextContent());
                    weight = Double.parseDouble(detail.getElementsByTagName("weight").item(0).getTextContent());
                    power = Double.parseDouble(detail.getElementsByTagName("power").item(0).getTextContent());
                    engine = new Engine(cost, nameEng, info, weight, typeEng, power);
                }
                if (landgear != null && avionic != null && fuselage != null && engine != null)
                    planeArray.add(new AnyBuilder(name, flightRange, landgear, avionic, fuselage, engine));

            }
        }
        return planeArray;
    }
}
