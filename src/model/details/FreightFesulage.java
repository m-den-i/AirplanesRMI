package model.details;

/**
 * Created by Denis on 10/11/2014.
 */
public class FreightFesulage extends Fuselage{
    public FreightFesulage(double cost, String name, String info, double weight, double len,
                            double flvol, double ld, int cpc){
        super(cost, name, info, weight, len, flvol);
        load = ld;
        capacityPass = cpc;
    }

    @Override
    public String toString() {
        return super.toString() + "\nFright Airplane\n---------------n";
    }


}
