package utils;

import java.util.Objects;

/**
 * Created by Mirco on 30.09.2016.
 */
public class SoftCardinality {

    public static double calculateSoftCardinality(Object[] set, TwoDimensionalMap map) {
        double res = 0;

        for (Object o : set) {
            double sum = 0;
            for (Object temp : set) {
                sum += (double) map.getValue(o, temp);
            }
            res += (double) 1 / sum;
        }
        return res;
    }
}
