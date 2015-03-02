package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Denis on 10/13/2014.
 * Class for holding Air companies, singleton
 */
public class AirCompanyHolder implements Serializable{
    private ArrayList<AirCompany> AirCompanyList;
    private static AirCompanyHolder inst = null;

    private AirCompanyHolder() {
        AirCompanyList = new ArrayList<AirCompany>();
    }

    public void addAirCompany(String nm, String info) {
        AirCompanyList.add(new AirCompany(nm, info));
    }

    public static AirCompanyHolder getInstance() {
        if (inst == null) {
            inst = new AirCompanyHolder();
            return inst;
        } else {
            return inst;
        }
    }

    public AirCompany getAC(String name)
    {
        for (AirCompany i : AirCompanyList)
        {
            if (name.equals(i.getName())) return i;
        }
        return null;
    }

    public ArrayList<AirCompany> getData(){
        return AirCompanyList;
    }

    public void setData(ArrayList<AirCompany> data){
        AirCompanyList = data;
    }
}
