package model.details;

/**
 * Created by Denis on 10/11/2014.
 */
public class LandGear extends AirPlaneDetail {
    public LandGear(double cost, String name, String info, double weight) {
        super(cost, name, info, weight);
    }

    @Override
    public String toString() {
        return "\n-----------\nLandgear:\n" + super.toString();
    }
}
