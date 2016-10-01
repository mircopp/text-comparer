package similarity.elements;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


/**
 * Created by Mirco on 30.09.2016.
 */
public class ElementSimilarityTest {

    private static final double DELTA = 1e-3;

    @Test
    public void testGetElementSimilarity(){
        StringEditDistance tester = new StringEditDistance();
        double result = tester.getElementSimilarity("Halli", "hallo");
        assertThat("Must be Greater!", result, greaterThan(0.5));
        result = tester.getElementSimilarity("hallo", "hallo");
        assertEquals(result, 1.0, DELTA);
        result = tester.getElementSimilarity("abcd", "efgh");
        assertEquals("Should be 0.0", result, 0.0, DELTA);

    }
}
