package spring;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import similarity.multiset.TextSimilarity;
import spring.models.TextStats;

/**
 * Created by Mirco on 01.10.2016.
 */

@RestController
public class TextSimilarityController {

    @RequestMapping(value = "/getSimilarity", method = RequestMethod.POST)
    public Response getSimilarity (@RequestBody TextStats data){

        TextSimilarity sim = new TextSimilarity(data.getText1(), data.getText2());
        return new Response(sim.getDiceCoefficientSimilarity());
    }

    public class Response{

        private double similarity;

        public Response(double sim){
            similarity = sim;
        }

        public double getSimilarity() {
            return similarity;
        }

        public void setSimilarity(double similarity) {
            this.similarity = similarity;
        }
    }

}

