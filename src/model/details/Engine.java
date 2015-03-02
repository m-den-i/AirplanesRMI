package model.details;

/**
 * Created by Denis on 10/11/2014.
 */
public class Engine extends AirPlaneDetail {
    public static enum type {REACTIVE, SHAFT};
    private double power;
    private type engineType;
    public Engine(double cost, String name, String info, double weight, String typ, double pow)
    {
        super(cost, name, info, weight);
        engineType = type.valueOf(typ);
        power = pow;
    }
    public String toString()
    {
        return "Engine, type: " + engineType.toString() + super.toString() + "\nPower: "  + power + "\n--------------\n";  }
}
