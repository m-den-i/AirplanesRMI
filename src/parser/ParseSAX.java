package parser;

import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.sun.org.apache.xpath.internal.operations.Bool;
import main.Main;
import model.apbuilding.AirPlaneModel;
import model.apbuilding.AnyBuilder;
import model.details.*;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

/**
 * Created by denis on 22.12.14.
 * Implements functional of SAX parser's
 */
public class ParseSAX {

    public static enum Detail{default_, new_plane, Fuselage, Engine, LandGear, Avionic};
    public final static int NAME = 1, INFO = 2, FLIGHTRANGE = 2, WEIGHT = 3, COST = 4, TYPE = 5, POWER = 6,
        LENGTH = 6, FUELVOLUME = 7, CAPACITY = 8, LOAD = 9, BAR = 10, ROCK = 10,
            BOMB = 11, PASSFUSELAGE = 10, FREIGHTFUSELAGE = 9, MILFUSELAGE = 11, ENGINE = 6,
            NOTENGINEDETAIL = 4, PLANE = 2;

    private final static ArrayList<AirPlaneModel> planeModels = new ArrayList<AirPlaneModel>();
    static int planeCount, fusCount, engineCount, detailCount;
    public static Fuselage buildFesulage(ArrayList<String> arrayList, int offset) {
            switch (Integer.parseInt(arrayList.get(offset + TYPE))){
                case AirPlaneModel.PASS:{
                    fusCount += PASSFUSELAGE;
                    return new PassengerFesulage(Double.parseDouble(arrayList.get(offset + COST)), arrayList.get(offset + NAME),
                            arrayList.get(offset + INFO), Double.parseDouble(arrayList.get(offset + WEIGHT)),
                            Double.parseDouble(arrayList.get(offset + LENGTH)),
                            Double.parseDouble(arrayList.get(offset + FUELVOLUME)), Boolean.valueOf(arrayList.get(offset + BAR)),
                            Integer.parseInt(arrayList.get(offset + CAPACITY)), Double.parseDouble(arrayList.get(offset + LOAD)));
                }
                case AirPlaneModel.FREIGHT:{
                    fusCount += FREIGHTFUSELAGE;
                    return new FreightFesulage(Double.parseDouble(arrayList.get(offset + COST)), arrayList.get(offset + NAME),
                            arrayList.get(offset + INFO), Double.parseDouble(arrayList.get(offset + WEIGHT)),
                            Double.parseDouble(arrayList.get(offset + LENGTH)), Double.parseDouble(arrayList.get(offset + FUELVOLUME)),
                            Double.parseDouble(arrayList.get(offset + LOAD)), Integer.parseInt(arrayList.get(offset + CAPACITY)));
                }
                case AirPlaneModel.MIL:{
                    fusCount += MILFUSELAGE;
                    int rock, bomb;
                    rock = Integer.parseInt(arrayList.get(offset + ROCK));
                    bomb = Integer.parseInt(arrayList.get(offset + BOMB));
                    return new MilitaryFuselage(Double.parseDouble(arrayList.get(offset + COST)), arrayList.get(offset + NAME),
                            arrayList.get(offset + INFO), Double.parseDouble(arrayList.get(offset + WEIGHT)),
                            Double.parseDouble(arrayList.get(offset + LENGTH)),
                            Double.parseDouble(arrayList.get(offset + FUELVOLUME)), rock, bomb,
                            Integer.parseInt(arrayList.get(offset + CAPACITY)), Double.parseDouble(arrayList.get(offset + LOAD)));
                }
                }
                return null;
    }

    public static Engine buildEngine(ArrayList<String> arrayList, int offset){
        return new Engine(Double.parseDouble(arrayList.get(offset + COST)), arrayList.get(offset + NAME),
                arrayList.get(offset + INFO), Double.parseDouble(arrayList.get(offset + WEIGHT)),
                arrayList.get(offset + TYPE), Double.parseDouble(arrayList.get(offset + POWER)));
    }

    public static LandGear buildLandGear(ArrayList<String> arrayList, int offset){
        return new LandGear(Double.parseDouble(arrayList.get(offset + COST)), arrayList.get(offset + NAME),
                arrayList.get(offset + INFO), Double.parseDouble(arrayList.get(offset + WEIGHT)));
    }

    public static Avionic buildAvionic(ArrayList<String> arrayList, int offset){
        return new Avionic(Double.parseDouble(arrayList.get(offset + COST)), arrayList.get(offset + NAME),
                arrayList.get(offset + INFO), Double.parseDouble(arrayList.get(offset + WEIGHT)));
    }

    public static ArrayList<AirPlaneModel> getArrayFromXML() {

        try {
            planeCount = 0; fusCount = 0; engineCount = 0; detailCount = 0;
            Logger log = Logger.getLogger(Main.class);
            log.debug("Read XML file with SAX parser method has started");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            log.debug("All the objects were created");
            final ArrayList<String> planeNames = new ArrayList<String>();
            final ArrayList<String> fusNames = new ArrayList<String>();
            final ArrayList<String> llandGearNames = new ArrayList<String>();
            final ArrayList<String> avionicNames = new ArrayList<String>();
            final ArrayList<String> engineNames = new ArrayList<String>();
            DefaultHandler handler = new DefaultHandler() {
                int index = 0;
                String curTag;
                Detail detail = Detail.default_;
                boolean opened = true;
                public void startElement(String uri, String localName, String tagName, Attributes attributes) throws SAXException {
                    try {
                        curTag = tagName;
                        opened = true;
                        detail = Detail.valueOf(curTag);
                    }
                    catch (IllegalArgumentException iaex){
                        //detail = Detail.default_;
                    }
                }
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    opened = false;
                }
                public void characters(char ch[], int start, int length) throws SAXException {
                    if (opened) {
                        if (detail == Detail.new_plane) {
                            planeNames.add(new String(ch, start, length));
                        }
                        if (detail == Detail.Fuselage) {
                            fusNames.add(new String(ch, start, length));
                        }
                        if (detail == Detail.LandGear) {
                            llandGearNames.add(new String(ch, start, length));
                        }
                        if (detail == Detail.Avionic) {
                            avionicNames.add(new String(ch, start, length));
                        }
                        if (detail == Detail.Engine) {
                            engineNames.add(new String(ch, start, length));
                        }
                    }
                }
            };

            saxParser.parse("AirPlaneModel.xml", handler);
           while (planeCount < planeNames.size() - 1){
               planeModels.add(new AnyBuilder(planeNames.get(planeCount + NAME),
                       Double.parseDouble(planeNames.get(planeCount + FLIGHTRANGE)),
                       buildLandGear(llandGearNames, detailCount), buildAvionic(avionicNames, detailCount),
                       buildFesulage(fusNames, fusCount), buildEngine(engineNames, engineCount)));
               planeCount += PLANE + 1;
               detailCount += NOTENGINEDETAIL + 1;
               engineCount += ENGINE + 1;
           }


            log.debug("File was parsed");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeModels;
    }

}
