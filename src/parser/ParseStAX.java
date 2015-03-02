package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import main.Main;
import model.apbuilding.AirPlaneModel;
import model.apbuilding.AnyBuilder;
import org.apache.log4j.Logger;

/**
 * Created by denis on 22.12.14.
 * Implements functional of StAX parser's
 */
public class ParseStAX{
    private static ArrayList<AirPlaneModel> planeModels;
    public static ArrayList<AirPlaneModel> getArrayFromXML() {
        try {
            int planeCount = -1, fusCount = -1, engineCount = -1, detailCount = -1;
            Logger log = Logger.getLogger(Main.class);
            log.debug("Read XML file with STAX parser method has started");
            ArrayList<String> planeNames = new ArrayList<String>();
            ArrayList<String> fusNames = new ArrayList<String>();
            ArrayList<String> llandGearNames = new ArrayList<String>();
            ArrayList<String> avionicNames = new ArrayList<String>();
            ArrayList<String> engineNames = new ArrayList<String>();
            planeModels = new ArrayList<AirPlaneModel>();
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("AirPlaneModel.xml"));
            log.debug("All the objects were created");
            int index = 0;
            String curTag;
            ParseSAX.Detail detail = ParseSAX.Detail.default_;
            boolean opened = true;
            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT: {
                        try {
                            curTag = reader.getLocalName();
                            detail = ParseSAX.Detail.valueOf(curTag);
                        }
                        catch (IllegalArgumentException iaex){
                            //detail = Detail.default_;
                        }
                        break;
                    }

                    case XMLStreamConstants.CHARACTERS: {
                        if (!reader.getText().trim().equals("")) {
                            if (detail == ParseSAX.Detail.new_plane) {
                                planeNames.add(reader.getText().trim());
                            }
                            if (detail == ParseSAX.Detail.Fuselage) {
                                fusNames.add(reader.getText().trim());
                            }
                            if (detail == ParseSAX.Detail.LandGear) {
                                llandGearNames.add(reader.getText().trim());
                            }
                            if (detail == ParseSAX.Detail.Avionic) {
                                avionicNames.add(reader.getText().trim());
                            }
                            if (detail == ParseSAX.Detail.Engine) {
                                engineNames.add(reader.getText().trim());
                            }
                        }
                        break;
                    }

                    case XMLStreamConstants.END_ELEMENT: {
                        break;
                    }
                    case XMLStreamConstants.START_DOCUMENT: {
                        break;
                    }

                }
            }

            log.debug("File was parsed");
            while (planeCount < planeNames.size() - 1){
                planeModels.add(new AnyBuilder(planeNames.get(planeCount + ParseSAX.NAME),
                        Double.parseDouble(planeNames.get(planeCount + ParseSAX.FLIGHTRANGE)),
                        ParseSAX.buildLandGear(llandGearNames, detailCount), ParseSAX.buildAvionic(avionicNames, detailCount),
                        ParseSAX.buildFesulage(fusNames, fusCount), ParseSAX.buildEngine(engineNames, engineCount)));
                planeCount += ParseSAX.PLANE;
                detailCount += ParseSAX.NOTENGINEDETAIL;
                engineCount += ParseSAX.ENGINE;
            }

        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        return planeModels;
    }

}
