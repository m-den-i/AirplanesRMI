package control;

import control.sortings.*;
import logging.MyLogger;
import model.*;
import model.apbuilding.AirPlaneModel;
import remote.DataCtrlIntf;
import serializer.AirportDrive;
import serializer.Connector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Logger;

/**
 * Created by Denis on 10/13/2014.
 * Controller, serve to process data
 */
public class DataCtrl implements DataCtrlIntf{
    private AirCompanyHolder Ach;
    private AirCompany currentAC = null;
    private BuilderHolder Apbh;
    private static DataCtrl instance = null;
    private static Logger log;
    private DataCtrl(){
        createAirCompanyHolder();
        createACH();
    }
    public void serialize(String path) throws IOException{
        Connector connect = new Connector();
        AirportDrive drive = new AirportDrive();
        drive.addAirCompanies(Ach.getData());
        drive.addAirplaneModels(Apbh.getData());
        connect.writeObj(drive, path);
        drive.refresh();
    }
    public void deserialize(String path) throws IOException,
            ClassNotFoundException{
        Connector connect = new Connector();
        AirportDrive drive = connect.readObj(path);
        Ach.setData(drive.getAirCompanies());
        Apbh.setData(drive.getAirplaneModels());

        drive.refresh();
        connect.close();
    }
    public static DataCtrl getInstance()throws IOException
    {
        if (instance == null) {
            instance = new DataCtrl();
            MyLogger.setup("ServerLogger");
            log = Logger.getLogger("ServerLogger");
            return instance;
        }
        else return instance;
    }

    public String [][] findPlanes(String [] input)
    {
        return currentAC.findPlanes(input);
    }

    public void SortCurrentAirCompanyByFlightRange()
    {
        currentAC.Sort(new FlightRange());
    }

    public void addNewAirport(String name, String info) {
        Ach.addAirCompany(name, info);
    }

    public void addNewBuilder(AirPlaneModel model){ Apbh.addBuilder(model); }

    public String[][] getAirPlaneList()
    {
        if (currentAC != null) {
            int count = currentAC.getSize();
            String[][] list = new String[count][2];
            for (int i = 0; i < count; i++) {
                list[i][0] = currentAC.getAirplane(i).getID();
                list[i][1] = currentAC.getAirplane(i).getName();
            }
            return list;
        }
        return  null;
    }

    public String[] getAirPlaneIDs()
    {
        if (currentAC != null) {
            int count = currentAC.getSize();
            String[] list = new String[count];
            for (int i = 0; i < count; i++) {
                list[i] = currentAC.getAirplane(i).getID();
            }
            return list;
        }
        return  null;
    }


    public void createAirCompanyHolder() { Apbh = BuilderHolder.getInstance(); }

    public void createACH()
    {
        Ach = AirCompanyHolder.getInstance();
    }

    public void setAirport(String name) {
        currentAC = Ach.getAC(name);
    }

    public void addAirplane(String name)
    {
        currentAC.addAirplane(Apbh.findModel(name));
    }

    public void setCurrentAirPlane(String ID)
    {
        currentAC.setCurrentAP(ID);
    }

    public String getAirCompanyInfo()
    {
        if (currentAC == null) return "EMPTY";
        double cap = 0, load = 0;
        for (int i = 0; i < currentAC.getSize(); i++)
        {
            cap += currentAC.getAirplane(i).getCapacity();
            load += currentAC.getAirplane(i).getLoad();
        }
        return currentAC.toString() + "Common capacity: " + cap + "\nCommon load: " + load + "\n";
    }

    public ArrayList<String> getAirCompanyNamesList(){
        ArrayList<String> list = new ArrayList<String>();
        for (AirCompany i: Ach.getData()){
            list.add(i.getName());
        }
        return list;
    }

    public ArrayList<String> getAirPlaneIDList(){
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < currentAC.getSize(); ++i){
            list.add(currentAC.getAirplane(i).getID());
        }
        return list;
    }

    public String getAirPlaneInfo()
    {
        return currentAC.getCurrentAP().toString();
    }

    public Vector <String> getBuilderNames()
    {
        return Apbh.getBuilderNames();
    }


}
