package utils;

/**
 * Created by Mirco on 30.09.2016.
 */
public abstract class SimilarityMeasures {

    public static double calculateDiceCoefficient(double intersection, double first, double second){
        return (2*intersection)/(first+second);
    }
}
