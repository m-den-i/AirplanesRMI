package serializer;

import model.AirCompany;
import model.apbuilding.AirPlaneModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Denis on 12/20/2014.
 * Abstract storage for saving collections to further
 * writing on disk
 */
public class AirportDrive implements Serializable {
    private ArrayList<AirCompany> airports;
    private ArrayList<AirPlaneModel> models;
    public AirportDrive(){
        airports = new ArrayList<AirCompany>();
        models = new ArrayList<AirPlaneModel>();
    }
    public void addAirCompanies(ArrayList<AirCompany> array_){
        airports = array_;
    }
    public void addAirplaneModels(ArrayList<AirPlaneModel> array_){
        models = array_;
    }
    public ArrayList<AirCompany> getAirCompanies(){
        return airports;
    }
    public ArrayList<AirPlaneModel> getAirplaneModels(){
        return models;
    }
    public void refresh(){
        airports = new ArrayList<AirCompany>();
        models = new ArrayList<AirPlaneModel>();
    }
}
