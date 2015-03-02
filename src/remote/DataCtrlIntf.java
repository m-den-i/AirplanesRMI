package remote;

import model.AirCompany;
import model.AirCompanyHolder;
import model.Airplane;
import model.BuilderHolder;
import model.apbuilding.AirPlaneModel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Comparator;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Denis
 * Date: 12/12/14
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 * Public interface providing remote controlling
 */
public interface DataCtrlIntf extends Remote {

    public String [][] findPlanes(String [] input) throws RemoteException;
    public void SortCurrentAirCompanyByFlightRange() throws RemoteException;
    public void addNewAirport(String name, String info) throws RemoteException;
    public void addNewBuilder(AirPlaneModel model) throws RemoteException;
    public String[][] getAirPlaneList() throws RemoteException;
    public String[] getAirPlaneIDs() throws RemoteException;
    public void createAirCompanyHolder() throws RemoteException;
    public void createACH() throws RemoteException;
    public void setAirport(String name) throws RemoteException;
    public void addAirplane(String name) throws RemoteException;
    public void setCurrentAirPlane(String ID) throws RemoteException;
    public String getAirCompanyInfo() throws RemoteException;
    public String getAirPlaneInfo() throws RemoteException;
    public Vector<String> getBuilderNames() throws RemoteException;
}
