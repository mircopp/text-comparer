package similarity.multiset;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import similarity.elements.StringEditDistance;
import similarity.sets.PhrasesSimilarity;
import utils.SimilarityMeasures;
import utils.SoftCardinality;
import utils.TwoDimensionalMap;

/**
 * Created by Mirco on 01.10.2016.
 */
public class TextSimilarity {

    private TwoDimensionalMap <String, Double> matrix;
    private String firstText;
    private String secondText;
    private String [] firstTextArray;
    private String [] secondTextArray;
    private String [] union;
    private double softCardinalityFirstText;
    private double softCardinalitySecondText;
    private double softCardinalityTextUnion;
    private PhrasesSimilarity measure;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public TextSimilarity(String text1, String text2){
        firstText = text1;
        secondText = text2;
        firstTextArray = formatStrings(text1);
        secondTextArray = formatStrings(text2);
        union = (String[])ArrayUtils.addAll(firstTextArray, secondTextArray);
        matrix = new TwoDimensionalMap<>();
        measure = new PhrasesSimilarity(StringEditDistance.class);
        for (String phrase1 : union){
            for (String phrase2 : union){
                measure.setPhrases(phrase1, phrase2);
                matrix.putValue(phrase1,phrase2, measure.getDiceCoefficientSimilarity());
            }
        }

        softCardinalityFirstText = SoftCardinality.calculateSoftCardinality(firstTextArray, matrix, 4.5);
        softCardinalitySecondText = SoftCardinality.calculateSoftCardinality(secondTextArray, matrix, 4.5);
        //really union or better union without duplicates?
        softCardinalityTextUnion = SoftCardinality.calculateSoftCardinality(union, matrix, 4.5);
    }

    public TwoDimensionalMap<String, Double> getMatrix() {
        return matrix;
    }

    public String getFirstText() {
        return firstText;
    }

    public String getSecondText() {
        return secondText;
    }

    public String[] getFirstTextArray() {
        return firstTextArray;
    }

    public String[] getSecondTextArray() {
        return secondTextArray;
    }

    public String[] getUnion() {
        return union;
    }

    public double getSoftCardinalityFirstText() {
        return softCardinalityFirstText;
    }

    public double getSoftCardinalitySecondText() {
        return softCardinalitySecondText;
    }

    public double getSoftCardinalityTextUnion() {
        return softCardinalityTextUnion;
    }
    public double getSoftCardinalityIntersection(){
        return getSoftCardinalityFirstText() + getSoftCardinalitySecondText() - getSoftCardinalityTextUnion();
    }

    public double getDiceCoefficientSimilarity(){
        return SimilarityMeasures.calculateDiceCoefficient(getSoftCardinalityIntersection(), getSoftCardinalityFirstText(), getSoftCardinalitySecondText());
    }

    private String[] formatStrings (String phrase){
        String [] res = phrase.split("[:.!?]");
        for (int i=0; i<res.length; i++){
            res[i] = deleteStartingWhitespaces(res[i]);
        }
        return res;
    }

    private String deleteStartingWhitespaces(String phrase){
        String res = phrase;
        while (res.startsWith(" ")||res.startsWith("\n")||res.startsWith("\t")){
            res = res.substring(1);
        }
        return res;
    }


}
