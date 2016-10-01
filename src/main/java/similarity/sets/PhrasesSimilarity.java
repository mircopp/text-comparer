package similarity.sets;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import similarity.elements.WordSimilarity;
import utils.SimilarityMeasures;
import utils.SoftCardinality;
import utils.TwoDimensionalMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mirco on 30.09.2016.
 */
public class PhrasesSimilarity {

    private String firstPhrase;
    private String secondPhrase;
    private String [] firstPhraseArray;
    private String [] secondPhraseArray;
    private String [] union;
    private double softCardinalityFirstPhrase;
    private double softCardinalitySecondPhrase;
    private double softCardinalityUnion;
    private TwoDimensionalMap <String, Double> matrix;
    private WordSimilarity elementSimilarity;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public PhrasesSimilarity(Class<? extends WordSimilarity> comparer){
        try {
            elementSimilarity = comparer.newInstance();
        }
        catch (Exception e){
            log.error("Error with setting up element similarity measure", e);
        }
    }

    public PhrasesSimilarity(String phrase1, String phrase2, Class <? extends WordSimilarity> comparer){
        try {
            elementSimilarity = comparer.newInstance();
        }
        catch (Exception e){
            log.error("Error with setting up element similarity measure", e);
        }
       setPhrases(phrase1, phrase2);
    }

    public void setPhrases (String first, String second){
        firstPhrase = first;
        secondPhrase = second;
        firstPhraseArray = first.replaceAll("[,.:\\\"/;'‘‚!?-]","").split("[ \n\t]");
        secondPhraseArray = second.replaceAll("[,.:\\\"/;‘‚'!?-]","").split("[ \n\t]");
        union = (String[]) ArrayUtils.addAll(firstPhraseArray, secondPhraseArray);
        matrix = new TwoDimensionalMap<String, Double>();
        for (String word1 : union){
            for (String word2 : union){
                matrix.putValue(word1,word2, elementSimilarity.getElementSimilarity(word1,word2));
            }
        }

        softCardinalityFirstPhrase = SoftCardinality.calculateSoftCardinality(firstPhraseArray, matrix , 1.0);
        softCardinalitySecondPhrase = SoftCardinality.calculateSoftCardinality(secondPhraseArray, matrix, 1.0);
        //really union or better union without duplicates?
        softCardinalityUnion = SoftCardinality.calculateSoftCardinality(union, matrix, 1.0);
    }

    public String [] getFirstPhraseArray(){
        return firstPhraseArray;
    }

    public String [] getSecondPhraseArray(){
        return secondPhraseArray;
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
