package model.apbuilding;

import model.details.*;

/**
 * Created by Denis on 10/14/2014.
 */
public class AN224Builder extends AirPlaneModel{

    public AN224Builder()
    {
        name = "AN224";
        flightRange = 1200;
        lndgr = new LandGear(19000, "Russian Wheels", "No info", 1000);
        avnc = new Avionic(100000, "Skolkovo Corp.", "No info", 200);
        fslg = new FreightFesulage(100000, "AviaCorpStroy", "No info", 250000, 90, 300000, 300000, 15);
        engn = new Engine(34000, "OAO Antonov", "No info", 15000, "REACTIVE", 250000);
    }
}
