package model.apbuilding;

import model.Airplane;

/**
 * Created by Denis on 10/11/2014.
 */
public class AirplaneBuilder {
    public static Airplane BuildAirplane(AirPlaneModel apmdl)
    {
       apmdl.buildEngine();
       apmdl.buildFuselage();
       apmdl.buildLandGear();
       apmdl.buildAvionic();
       apmdl.buildParams();
       return apmdl.getAirplane();

    }
}
