package similarity.sets;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;
import similarity.elements.StringEditDistance;
import utils.TwoDimensionalMap;

import static org.junit.Assert.*;

/**
 * Created by Mirco on 30.09.2016.
 */
public class PhrasesSimilarityTest {

    private static final double DELTA = 1e-3;

    private void printMatrix(String [] union, TwoDimensionalMap<String, Double> matrix){
        for (String word1 : union){
            for (String word2 : union){
                System.out.format("Similarity between %S and %S set to %f \n", word1, word2, matrix.getValue(word1, word2));
            }
        }

    }

    @Test
    public void testGetMatrix(){

        String first = "Hallo ich bins!";
        String second = "Wie geht es dir?";
        PhrasesSimilarity sim = new PhrasesSimilarity(first, second, StringEditDistance.class);
        TwoDimensionalMap <String, Double> matrix = sim.getMatrix();
        String [] union = (String[]) ArrayUtils.addAll(sim.getFirstPhraseArray(), sim.getSecondPhraseArray());
        for (int i = 0; i<union.length; i++){
            assertEquals("Diagonal axis should be egual 1.0!", matrix.getValue(union[i], union[i]), 1.0, DELTA);
        }
    }
    @Test
    public void testGetMatrix2(){

        String first = "Sergio Jiménez Vargas";
        String second = "Cergio Gimenez Bargaz";
        PhrasesSimilarity sim = new PhrasesSimilarity(first, second, StringEditDistance.class);
        TwoDimensionalMap <String, Double> matrix = sim.getMatrix();
        String [] union = (String[]) ArrayUtils.addAll(sim.getFirstPhraseArray(), sim.getSecondPhraseArray());
        for (int i = 0; i<union.length; i++){
            assertEquals("Diagonal axis should be egual 1.0!", matrix.getValue(union[i], union[i]), 1.0, DELTA);
        }
    }

    @Test
    public void testGetSoftcardinality(){

        String first = "Sergio Jiménez Vargas";
        String second = "Cergio Gimenez Bargaz";
        PhrasesSimilarity sim = new PhrasesSimilarity(first, second, StringEditDistance.class);
        double softCardinalityFirstPhrase = sim.getSoftCardinalityFirstPhrase();
        assertEquals("Should be 2.5, but was " + softCardinalityFirstPhrase, softCardinalityFirstPhrase, 2.5, DELTA );
        double softCardinalitySecondPhrase = sim.getSoftCardinalitySecondPhrase();
        assertEquals("Should be 2.302, but was " + softCardinalitySecondPhrase, softCardinalitySecondPhrase, 2.302, DELTA );
    }

    @Test
    public void testGetSoftcardinalityUnion(){

        String first = "Hallo ich bins";
        String second = "Hallo ich bins";
        PhrasesSimilarity sim = new PhrasesSimilarity(first, second, StringEditDistance.class);
        double softCardinalityFirstPhrase = sim.getSoftCardinalityFirstPhrase();
        double softCardinalitySecondPhrase = sim.getSoftCardinalitySecondPhrase();
        assertEquals("Should be equal, but was " + softCardinalityFirstPhrase + " and " + softCardinalitySecondPhrase, softCardinalityFirstPhrase, softCardinalitySecondPhrase, DELTA );
        double softCardinalityUnion = sim.getSoftCardinalityUnion();
        assertEquals("Should be equal, but was " + softCardinalityFirstPhrase + " and " + softCardinalityUnion, softCardinalityFirstPhrase, softCardinalityUnion, DELTA );
    }
    @Test
    public void testGetSoftcardinalityUnion2(){

        String first = "Sergio Jiménez Vargas";
        String second = "Cergio Gimenez Bargaz";
        PhrasesSimilarity sim = new PhrasesSimilarity(first, second, StringEditDistance.class);
        double union = sim.getSoftCardinalityUnion();
        assertEquals("Should be 2.687, but was " + union, union, 2.687, DELTA );

    }

    @Test
    public void testGetSoftcardinalityIntersection(){

        String first = "Sergio Jiménez Vargas";
        String second = "Cergio Gimenez Bargaz";
        PhrasesSimilarity sim = new PhrasesSimilarity(first, second, StringEditDistance.class);
        double intersection = sim.getSoftCardinalityIntersection();
        assertEquals("Should be 2.115, but was " + intersection, intersection, 2.115, DELTA );

    }

    @Test
    public void testGetDiceCoefficientSimilarity(){

        String first = "Sergio Jiménez Vargas";
        String second = "Cergio Gimenez Bargaz";
        PhrasesSimilarity sim = new PhrasesSimilarity(first, second, StringEditDistance.class);
        double dice = sim.getDiceCoefficientSimilarity();
        assertEquals("Should be 0.880, but was " + dice, dice, 0.880, DELTA );

    }
}
