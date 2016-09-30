package similarity.phrases;

import org.junit.Test;
import utils.TwoDimensionalMap;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Base64;
import java.util.Map;

/**
 * Created by Mirco on 30.09.2016.
 */
public class PhrasesSimilarityTest {

    private static final double DELTA = 1e-15;

    private void printMatrix(String firstPhrase, String secondPhrase, TwoDimensionalMap<String, Double> matrix){
        String [] first = firstPhrase.split(" ");
        String [] second = secondPhrase.split(" ");
        System.out.print("\n");
        for (String word1 : first){
            for (String word2 : second){
                System.out.format("Similarity between %S and %S set to %f \n", word1, word2, matrix.getValue(word1, word2));
            }
        }

    }

    @Test
    public void testGetMatrix(){

        String first = "Hallo ich bins";
        String second = "Hallo ich bins";
        PhrasesSimilarity sim = new PhrasesSimilarity(first, second);
        TwoDimensionalMap <String, Double> matrix = sim.getMatrix();
        String [] firstArray = sim.getFirstPhrase();
        String [] secondArray = sim.getSecondPhrase();
        for (int i = 0; i<firstArray.length; i++){
            assertEquals("Diagonal axis should be egual 1.0!", matrix.getValue(firstArray[i], secondArray[i]), 1.0, DELTA);
        }
    }
}
