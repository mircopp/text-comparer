package similarity.phrases;

import org.apache.commons.lang.ArrayUtils;
import similarity.elements.StringEditDistance;
import similarity.set.SimilarityMeasures;
import utils.SoftCardinality;
import utils.TwoDimensionalMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mirco on 30.09.2016.
 */
public class PhrasesSimilarity {

    private String [] firstPhrase;
    private String [] secondPhrase;
    private double softCardinalityFirstPhrase;
    private double softCardinalitySecondPhrase;
    private double softCardinalityUnion;
    private TwoDimensionalMap <String, Double> matrix;
    private StringEditDistance editDistance;

    public PhrasesSimilarity(String phrase1, String phrase2){
        firstPhrase = phrase1.replaceAll("[,.:\\\"/;'!?-]","").split(" ");
        secondPhrase = phrase2.replaceAll("[,.:\\\"/;'!?-]","").split(" ");
        String [] union = (String[]) ArrayUtils.addAll(firstPhrase, secondPhrase);
        editDistance = new StringEditDistance();
        matrix = new TwoDimensionalMap<String, Double>();
        for (String word1 : union){
            for (String word2 : union){
                matrix.putValue(word1,word2, editDistance.getElementSimilarity(word1,word2));
            }
        }

        softCardinalityFirstPhrase = SoftCardinality.calculateSoftCardinality(firstPhrase, matrix);
        softCardinalitySecondPhrase = SoftCardinality.calculateSoftCardinality(secondPhrase, matrix);

        //really union or better union without duplicates?
        softCardinalityUnion = SoftCardinality.calculateSoftCardinality(union, matrix);
    }

    public double calculateSimilarity(){
        return 0;

    }

    public String [] getFirstPhrase(){
        return firstPhrase;
    }

    public String [] getSecondPhrase(){
        return secondPhrase;
    }

    public TwoDimensionalMap<String, Double> getMatrix (){
        return matrix;
    }

    public double getSoftCardinalityFirstPhrase(){
        return softCardinalityFirstPhrase;
    }

    public double getSoftCardinalitySecondPhrase(){
        return softCardinalitySecondPhrase;
    }

    public double getSoftCardinalityUnion(){
        return softCardinalityUnion;
    }

    public double getSoftCardinalityIntersection(){
        return softCardinalityFirstPhrase + softCardinalitySecondPhrase - softCardinalityUnion;
    }

    public double getDiceCoefficientSimilarity(){
        return SimilarityMeasures.calculateDiceCoefficient(getSoftCardinalityIntersection(), getSoftCardinalityFirstPhrase(), getSoftCardinalitySecondPhrase());
    }

    private String[] getUnion (String[] first, String [] second){
        Set<String> unionSet = new HashSet<String>(Arrays.asList(first));
        unionSet.addAll(Arrays.asList(second));
        String [] res = unionSet.toArray(new String[unionSet.size()]);
        return res;
    }

}
