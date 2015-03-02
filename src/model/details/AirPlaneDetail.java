package model.details;

import java.io.Serializable;

/**
 * Created by Denis on 10/11/2014.
 */
public abstract class AirPlaneDetail implements Serializable {
    protected double weight;
    protected double cost;
    protected String name;
    protected String info;

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "\nName: " + name + "\nInfo: " + info + "\nCost:" + cost + "\nWeight: " + weight + "\n";
    }

    public double getWeight() {
        return weight;
    }

    public AirPlaneDetail(double cost, String name, String info, double weight)
    {
        this.cost = cost;
        this.name = name;
        this.info = info;
        this.weight = weight;
    }
}
