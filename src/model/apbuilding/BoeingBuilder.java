package model.apbuilding;

import model.details.Avionic;
import model.details.Engine;
import model.details.LandGear;
import model.details.PassengerFesulage;

/**
 * Created by Denis on 10/11/2014.
 */
public class BoeingBuilder extends AirPlaneModel{

    public BoeingBuilder()
    {
        name = "Boeing 777";
        flightRange = 1000;
        lndgr = new LandGear(9500, "Wheels corp.", "No info", 250);
        avnc = new Avionic(100000, "AviaTech LTD", "No info", 200);
        fslg = new PassengerFesulage(25000, "USA Systems", "No info", 20000, 70, 200000, true, 600, 1000);
        engn = new Engine(10000, "Boeing engine", "No info", 4000, "REACTIVE", 50000);
    }
}

