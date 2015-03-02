package control.sortings;

import model.Airplane;

import java.util.Comparator;

/**
 * Created by Denis on 10/18/2014.
 */
public class FlightRange implements Comparator<Airplane>{
    @Override
    public int compare(Airplane airplane, Airplane airplane2) {
        double n = airplane.getFlightRange() - airplane2.getFlightRange();
        if (n > 0) return 1;
        if (n < 0) return -1;
        return 0;
    }

}
