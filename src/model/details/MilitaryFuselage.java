package model.details;

/**
 * Created by Denis on 10/11/2014.
 */
public class MilitaryFuselage extends Fuselage
{
    private int rockets;
    private int bombs;
    public MilitaryFuselage(double cost, String name, String info, double weight, double len,
                            double flvol, int rock, int bmb, int cpc, double ld){
        super(cost, name, info, weight, len, flvol);
        rockets = rock;
        bombs = bmb;
        capacityPass = cpc;
        load = ld;
    }
    @Override
    public String toString() {
        return super.toString() + "\nMilitary Airplane"+
                "\nWeapon (rock, bombs): " + rockets + ", " + bombs + "\n-------------------\n";
    }
}
