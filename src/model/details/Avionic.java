package model.details;

/**
 * Created by Denis on 10/11/2014.
 */
public class Avionic extends AirPlaneDetail {
    public Avionic(double cost, String name, String info, double weight) {
        super(cost, name, info, weight);
    }

    @Override
    public String toString() {
        return "Avionic:\n" + super.toString();
    }
}
