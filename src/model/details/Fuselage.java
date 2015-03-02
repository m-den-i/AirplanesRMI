package model.details;

/**
 * Created by Denis on 10/11/2014.
 */
public abstract class Fuselage extends AirPlaneDetail {
    protected double length;
    protected double fuelVolume;
    protected int capacityPass;
    protected double load;
    public Fuselage(double cost, String name, String info, double weight, double len, double flvol) {
        super(cost, name, info, weight);
        length = len;
        fuelVolume = flvol;
    }

    //@Override
    public String toString() {
        return "Fuselage params:\n" + super.toString() + "\nLength: " + length +
                "\nFuel capacity: " + fuelVolume + "\nPassengers Capacity: " + capacityPass + "\nMax Load: " + load + "\n" ;
    }

    public double getLoad()
    {
        return load;
    }

    public int getCapacityPass()
    {
        return capacityPass;
    }
}
