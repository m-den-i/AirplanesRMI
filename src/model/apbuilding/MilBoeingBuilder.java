package model.apbuilding;

import model.details.*;

/**
 * Created by Denis on 10/14/2014.
 */
public class MilBoeingBuilder extends AirPlaneModel{

    public MilBoeingBuilder()
    {
        name = "Boeing MIL3";
        flightRange = 700;
        avnc = new Avionic(150000, "AviaTech LTD", "No info", 200);
        lndgr = new LandGear(9500, "Wheels corp.", "No info", 250);
        fslg = new MilitaryFuselage(25000, "USA Systems", "No info", 20000, 45, 100000, 5, 3, 2, 1000);
        engn = new Engine(10000, "Boeing engine", "No info", 4000, "REACTIVE", 50000);
    }
}
