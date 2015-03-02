package model;

import model.apbuilding.*;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Denis on 10/14/2014.
 * Class-adapter for concrete builders - models of planes, singleton
 */
public class BuilderHolder {
    ArrayList<AirPlaneModel> builderList;
    private static BuilderHolder inst = null;

    private BuilderHolder() {
        builderList = new ArrayList<AirPlaneModel>();
        builderList.add(new AerobusBuilder());
        builderList.add(new BoeingBuilder());
        builderList.add(new MilBoeingBuilder());
        builderList.add(new AN224Builder());
    }

    public void addBuilder(AirPlaneModel bldr) {
        builderList.add(bldr);
    }

    public static BuilderHolder getInstance() {
        if (inst == null) {
            inst = new BuilderHolder();
            return inst;
        } else {
            return inst;
        }
    }

    public AirPlaneModel findModel(String name)
    {
        for (AirPlaneModel i : builderList)
        {
            if (name.equals(i.toString()))
            {
                return i;
            }
        }
        return null;
    }

    public String getNameAt(int i)
    {
        return builderList.get(i).toString();
    }

    public Vector<String> getBuilderNames()
    {
        Vector <String> list = new Vector<String>();
        for (AirPlaneModel i : builderList)
        {
            list.add(i.toString());
        }
        return list;
    }

    public ArrayList<AirPlaneModel> getData(){
        return builderList;
    }

    public void setData(ArrayList<AirPlaneModel> data){
        builderList = data;
    }
 }
