package similarity.phrases;

import similarity.elements.StringEditDistance;
import utils.TwoDimensionalMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mirco on 30.09.2016.
 */
public class PhrasesSimilarity {

    StringEditDistance wordDistance;
    String [] firstPhrase;
    String [] secondPhrase;
    TwoDimensionalMap <String, Double> matrix;
    StringEditDistance editDistance;

    public PhrasesSimilarity(String phrase1, String phrase2){
        firstPhrase = phrase1.split(" ");
        secondPhrase = phrase2.split(" ");
        editDistance = new StringEditDistance();
        matrix = new TwoDimensionalMap<String, Double>();
        for (String word1 : firstPhrase){
            for (String word2 : secondPhrase){
                matrix.putValue(word1,word2, editDistance.getElementSimilarity(word1,word2));
            }
        }
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
}
