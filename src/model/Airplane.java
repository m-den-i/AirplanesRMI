package model;

import model.details.Avionic;
import model.details.Engine;
import model.details.Fuselage;
import model.details.LandGear;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Denis on 10/11/2014.
 * Airplane class
 */
public class Airplane implements Serializable{

    private Engine engn;
    private Fuselage fslg;
    private LandGear lndgr;
    private Avionic avnc;
    private String name;
    private String ID;
    private double flightRange;
    private Date bought;


    public void setEngn(Engine engn) {
        this.engn = engn;
    }

    public void setFslg(Fuselage fslg) {
        this.fslg = fslg;
    }

    public void setLndgr(LandGear lndgr) {
        this.lndgr = lndgr;
    }

    public void setAvnc(Avionic avnc) {
        this.avnc = avnc;
    }

    public void setFlightRange(double flightRange) {
        this.flightRange = flightRange;
    }

    public void setBought(Date dt)
    {
        bought  = dt;
        ID = String.valueOf(Math.abs((dt.toString()).hashCode()));
    }

    public void setName(String nm)
    {
        name = nm;
    }

    public double getCost()
    {
       return engn.getCost() + lndgr.getCost() + fslg.getCost() + avnc.getCost();
    }

    public double getWeight()
    {
        return engn.getWeight() + lndgr.getWeight() + fslg.getWeight() + avnc.getWeight();
    }

    public String getName() {return name;}

    public double getFlightRange()
    {
        return flightRange;
    }

    public double getLoad()
    {
        return fslg.getLoad();
    }

    public int getCapacity()
    {
        return fslg.getCapacityPass();
    }

    public String getID()
    {
        return ID;
    }

    public String getBought()
    {
        return bought.toString();
    }

    public Date getBoughtinDate()
    {
        return bought;
    }

    public String toString()
    {
        String res = "Airplane - " + name + "\nFlight range: " + flightRange + "\nWeight: " + getWeight() + "\nBought: " + bought.toString()
                + "\nCost: " + getCost() + "\n";

        return res + "\n-----------\n" + fslg.toString() + engn.toString() + avnc
                .toString() + lndgr.toString();


    }
}
