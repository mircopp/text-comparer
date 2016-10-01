package utils;

import java.util.Objects;

/**
 * Created by Mirco on 30.09.2016.
 */
public abstract class SoftCardinality {

    public static double calculateSoftCardinality(Object[] set, TwoDimensionalMap map, double p) {
        double res = 0;

        for (Object o : set) {
            double sum = 0;
            for (Object temp : set) {
                sum += Math.pow((double) map.getValue(o, temp), p);
            }
            res += (double) 1 / sum;
        }
        return res;
    }
}
