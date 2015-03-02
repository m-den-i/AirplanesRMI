package model.apbuilding;

import model.Airplane;
import model.details.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Denis on 10/11/2014.
 */
public abstract class AirPlaneModel implements Serializable{
    public static final int PASS = 0;
    public static final int MIL = 1;
    public static final int FREIGHT = 2;
    protected Airplane apl = new Airplane();
    protected Engine engn;
    protected Fuselage fslg;
    protected LandGear lndgr;
    protected Avionic avnc;
    protected String name;
    protected double flightRange;
    public void buildEngine()
    {
        apl.setEngn(engn);
    }
    public void buildFuselage()
    {
        apl.setFslg(fslg);
    }
    public void buildLandGear()
    {
        apl.setLndgr(lndgr);
    }
    public void buildAvionic()
    {
        apl.setAvnc(avnc);
    }

    public void buildParams()
    {
        apl.setFlightRange(flightRange);
        apl.setBought(new Date());
        apl.setName(name);
    }

    public Airplane getAirplane()
    {
        Airplane ret = apl;
        apl = new Airplane();
        return ret;

    }
    public String toString()
    {
        return name;
    }
}
