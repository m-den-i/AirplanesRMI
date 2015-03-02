package model.details;

/**
 * Created by Denis on 10/11/2014.
 */
public class PassengerFesulage extends Fuselage{
    boolean bar;
    public PassengerFesulage(double cost, String name, String info, double weight, double len,
                           double flvol, boolean br, int cpc, double ld){
        super(cost, name, info, weight, len, flvol);
        capacityPass = cpc;
        load = ld;
    }
    @Override
    public String toString() {
        return super.toString() + "\nPassenger Airplane\n---------------\n";
    }
}
