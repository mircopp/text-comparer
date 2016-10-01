package similarity.elements;

import org.apache.airavata.samples.LevenshteinDistanceService;
import org.hibernate.validator.cfg.defs.LengthDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mirco on 30.09.2016.
 */
public class StringEditDistance extends WordSimilarity {

    LevenshteinDistanceService editDistance;
    Logger log = LoggerFactory.getLogger(this.getClass());

    public StringEditDistance(){

        editDistance = new LevenshteinDistanceService();
    }

    @Override
    public double getElementSimilarity(Object element1, Object element2) {
        if (element1 instanceof String && element2 instanceof String){
            String element1String = ((String) element1).toLowerCase();
            String element2String = ((String) element2).toLowerCase();
            int distance = editDistance.computeDistance(element1String, element2String);
            int maxLength = Math.max(element1String.length(), element2String.length());
            double quotient = (double) distance/maxLength;
            double result = 1 - (quotient);
            return result;
        }
        else {
            throw new IllegalArgumentException("Input must be String!");
        }
    }

    @Override
    public String getComparingMethod() {
        return "Levenshtein Edit Distance";
    }
}
