package model.apbuilding;

import model.details.Avionic;
import model.details.Engine;
import model.details.LandGear;
import model.details.PassengerFesulage;

/**
 * Created by Denis on 10/13/2014.
 */
public class AerobusBuilder extends AirPlaneModel{


    public AerobusBuilder()
    {
        name = "Aerobus A381";
        flightRange = 1000;
        fslg = new PassengerFesulage(25000, "AviEurope Corp.", "No info", 20000, 70, 200000, false, 600, 1200);
        lndgr = new LandGear(9500, "Wheels of France", "No info", 250);
        avnc = new Avionic(100000, "Intel", "No info", 200);
        engn = new Engine(15000, "Aerobus engine", "No info", 4000, "REACTIVE", 50000);
    }
}
