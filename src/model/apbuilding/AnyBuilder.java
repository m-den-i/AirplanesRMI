package model.apbuilding;
import model.details.*;

/**
 * Created by Denis on 12/21/2014.
 */
public class AnyBuilder extends AirPlaneModel {
    public AnyBuilder(String name, double fRange, LandGear landGear, Avionic avionic, Fuselage fuselage, Engine engine){
        this.name = name;
        flightRange = fRange;
        lndgr = landGear;
        avnc = avionic;
        fslg = fuselage;
        engn = engine;
    }
}
