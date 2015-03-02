package model;

import model.apbuilding.AirPlaneModel;
import model.apbuilding.AirplaneBuilder;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by Denis on 10/11/2014.
 * Air company class
 */
public class AirCompany implements Serializable {
    private ArrayList<Airplane> airplaneList;
    private String name;
    private String info;
    private Airplane currentAP;

    public AirCompany(String nm, String inf) {
        name = nm;
        airplaneList = new ArrayList<Airplane>();
        info = inf;
    }

    public String [][] findPlanes(String [] data)
    {
        String [][] list = new String[airplaneList.size()][2];
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ROOT);
        Date dbg, dend;
        int j = 0;
        Double fbg, fend, cbg, cend, lbg, lend;
        try {
            if (data[0].equals( "")) data[0] = "01/01/1900";
            if (data[1].equals( "")) data[1] = "01/01/9999";
            dbg = df.parse(data[0]);
            dend = df.parse(data[1]);
            if (data[2].equals( "")) data[2] = "0";
            if (data[3].equals( "")) data[3] = "999999";
            fbg = Double.valueOf(data[2]);
            fend = Double.valueOf(data[3]);
            if (data[4].equals( "")) data[4] = "0";
            if (data[5].equals( "")) data[5] = "99999999";
            cbg = Double.valueOf(data[4]);
            cend = Double.valueOf(data[5]);
            if (data[6].equals( "")) data[6] = "0";
            if (data[7].equals( "")) data[7] = "99999999999";
            lbg = Double.valueOf(data[6]);
            lend = Double.valueOf(data[7]);
            for (int i = 0; i < airplaneList.size(); i++)
            {
                if (airplaneList.get(i).getBoughtinDate().after(dbg) && airplaneList.get(i).getBoughtinDate().before(dend))
                    if (airplaneList.get(i).getFlightRange() >= fbg && airplaneList.get(i).getFlightRange() <= fend)
                        if (airplaneList.get(i).getCapacity() >= cbg && airplaneList.get(i).getCapacity() <= cend)
                            if (airplaneList.get(i).getLoad() >= lbg && airplaneList.get(i).getLoad() <= lend) {
                                list[j][0] = airplaneList.get(i).getID();
                                list[j][1] = airplaneList.get(i).getName();
                                j++;
                            }



            }
        }
        catch (Exception ecc)
        {
            return null;
        }
        return list;
    }

    public void Sort(Comparator<Airplane> comp)
    {
        Airplane [] apl = new Airplane[airplaneList.size()];
        for (int i = 0; i < apl.length; i++)
        {
            apl[i]  = airplaneList.get(i);
        }
       Arrays.sort(apl, comp);
        airplaneList.clear();
       for (int i = 0; i < apl.length; i++)
       {
           airplaneList.add(apl[i]);
       }
    }

    public String toString() {
        return "Air company name: " + name + "\nInfo: " + info + "\nAirplanes found: " + airplaneList.size() + "\n";
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return airplaneList.size();
    }


    public Airplane getAirplaneID(int id) {
        for (Airplane i : airplaneList) {
            if (i.getID().equals(String.valueOf(id)))
                return i;
        }
        return null;
    }

    public Airplane getAirplane(int i) {

            if (i < airplaneList.size())
                return airplaneList.get(i);

        return null;
    }

    public void setCurrentAP(String id)
    {
        for (Airplane i : airplaneList)
        {
            currentAP = null;
            if (i.getID().equals(id))
            {
                currentAP = i;
                break;
            }
        }
    }

    public void addAirplane(AirPlaneModel builder)
    {
        airplaneList.add(AirplaneBuilder.BuildAirplane(builder));
    }

    public Airplane getCurrentAP()
    {
        return currentAP;
    }
}
