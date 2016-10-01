package similarity.multiset;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;



/**
 * Created by Mirco on 01.10.2016.
 */
public class TextSimilarityTest {

    private static final double DELTA = 1e-3;
    @Test
    public void testGetDiceCoeffientSameTopic(){
        String text1 = "Ein Plagiat ist die Anmaßung fremder geistiger Leistungen. " +
                "Dies kann sich auf die Übernahme fremder Texte oder anderer Darstellungen, fremder Ideen oder beides gleichzeitig beziehen. " +
                "Plagiate können, müssen aber nicht, gegen das Gesetz verstoßen: " +
                "Die nicht als Zitat gekennzeichnete Übernahme fremder Texte ist in der Regel eine Verletzung von Urheberrechten. " +
                "Die Übernahme fremder Ideen kann eine Verletzung von Patentrechten oder Geschmacksmustern sein. " +
                "In der Wissenschaft kann ein Plagiat gegen Prüfungsordnungen, Arbeitsverträge oder Universitätsrecht verstoßen. " +
                "Zwischen rechtswidrigen Übernahmen fremder geistiger Leistungen und der legitimen Übernahme freier oder frei gewordener Ideen gibt es eine Grauzone, wo ein Plagiat zwar als legal, nicht aber als legitim gilt.";
        String text2 = "Ein Plagiat ist die widerrechtliche Übernahme und Verbreitung von fremden Texten jeglicher Art und Form ohne Kenntlichmachung der Quelle. " +
                "Dies gilt für alle Medien. " +
                "Die häufigsten Formen des Plagiats in wissenschaftlichen Arbeiten sind:\n" +
                "Die wörtliche Übernahme einer oder mehrerer Textpassagen ohne entsprechende Quellenangabe (Textplagiat).\n" +
                "Die Wiedergabe beziehungsweise Paraphrasierung eines Gedankengangs, wobei Wörter und der Satzbau des " +
                "Originals so verändert werden, dass der Ursprung des Gedankens verwischt wird (Ideenplagiat).\n" +
                "Die Übersetzung von Ideen und Textpassagen aus einem fremdsprachigen Werk, wiederum ohne " +
                "Quellenangabe.\n" +
                "Die Übernahme von Metaphern, Idiomen oder eleganten sprachlichen Schöpfungen ohne " +
                "Quellenangabe.\n" +
                "Die Verwendung von Zitaten, die man in einem Werk der Sekundärliteratur angetroffen hat, zur " +
                "Stützung eines eigenen Arguments, wobei zwar die Zitate selbst dokumentiert werden, nicht aber " +
                "die verwendete Sekundärliteratur (Zitatsplagiat).";

        TextSimilarity sim = new TextSimilarity(text1, text2);
        System.out.println(sim.getDiceCoefficientSimilarity());

    }
    @Test
    public void testGetDiceCoeffientSameText(){
        String text1 = "Ein Plagiat ist die Anmaßung fremder geistiger Leistungen. " +
                "Dies kann sich auf die Übernahme fremder Texte oder anderer Darstellungen, fremder Ideen oder beides gleichzeitig beziehen. " +
                "Plagiate können, müssen aber nicht, gegen das Gesetz verstoßen: " +
                "Die nicht als Zitat gekennzeichnete Übernahme fremder Texte ist in der Regel eine Verletzung von Urheberrechten. " +
                "Die Übernahme fremder Ideen kann eine Verletzung von Patentrechten oder Geschmacksmustern sein. " +
                "In der Wissenschaft kann ein Plagiat gegen Prüfungsordnungen, Arbeitsverträge oder Universitätsrecht verstoßen. " +
                "Zwischen rechtswidrigen Übernahmen fremder geistiger Leistungen und der legitimen Übernahme freier oder frei gewordener Ideen gibt es eine Grauzone, wo ein Plagiat zwar als legal, nicht aber als legitim gilt.";
        String text2 = "Ein Plagiat ist die Anmaßung fremder geistiger Leistungen. " +
                "Dies kann sich auf die Übernahme fremder Texte oder anderer Darstellungen, fremder Ideen oder beides gleichzeitig beziehen. " +
                "Plagiate können, müssen aber nicht, gegen das Gesetz verstoßen: " +
                "Die nicht als Zitat gekennzeichnete Übernahme fremder Texte ist in der Regel eine Verletzung von Urheberrechten. " +
                "Die Übernahme fremder Ideen kann eine Verletzung von Patentrechten oder Geschmacksmustern sein. " +
                "In der Wissenschaft kann ein Plagiat gegen Prüfungsordnungen, Arbeitsverträge oder Universitätsrecht verstoßen. " +
                "Zwischen rechtswidrigen Übernahmen fremder geistiger Leistungen und der legitimen Übernahme freier oder frei gewordener Ideen gibt es eine Grauzone, wo ein Plagiat zwar als legal, nicht aber als legitim gilt.";


        TextSimilarity sim = new TextSimilarity(text1, text2);
        assertEquals(String.format("Should be 1 but was %f.", sim.getDiceCoefficientSimilarity()), sim.getDiceCoefficientSimilarity(), 1.0, DELTA);
        System.out.println(sim.getDiceCoefficientSimilarity());

    }

    @Test
    public void testGetDiceCoefficientTotallyDifferentTopics(){
        String text1 = "Hallo, mein Name ist Klaus." +
                "Wie geht es dir?";
        String text2 = "Das Wetter ist schön." +
                "Sollen wir etwas unternehmen?";

        TextSimilarity sim = new TextSimilarity(text1, text2);
        assertThat(String.format("Should be less than 0.5, but was %f", sim.getDiceCoefficientSimilarity()), sim.getDiceCoefficientSimilarity(), lessThan(0.5));
        System.out.println(sim.getDiceCoefficientSimilarity());
    }


    @Test
    public void testGetDiceCoeffientDifferentTopics(){
        String text1 = "Ein Plagiat ist die Anmaßung fremder geistiger Leistungen. " +
                "Dies kann sich auf die Übernahme fremder Texte oder anderer Darstellungen, fremder Ideen oder beides gleichzeitig beziehen. " +
                "Plagiate können, müssen aber nicht, gegen das Gesetz verstoßen: " +
                "Die nicht als Zitat gekennzeichnete Übernahme fremder Texte ist in der Regel eine Verletzung von Urheberrechten. " +
                "Die Übernahme fremder Ideen kann eine Verletzung von Patentrechten oder Geschmacksmustern sein. " +
                "In der Wissenschaft kann ein Plagiat gegen Prüfungsordnungen, Arbeitsverträge oder Universitätsrecht verstoßen. " +
                "Zwischen rechtswidrigen Übernahmen fremder geistiger Leistungen und der legitimen Übernahme freier oder frei gewordener Ideen gibt es eine Grauzone, wo ein Plagiat zwar als legal, nicht aber als legitim gilt.";
        String text2 = "Schuhcreme ist ein wachshaltiges, salben-, pasten- oder gelartiges Gemisch zur Lederpflege, meistens von Schuhschäften aus Glattleder. " +
                "Chemisch handelt es sich, wie bei vielen Cremes, um eine Dispersion beziehungsweise Suspension. " +
                "Schuhcremes zählen zu den oberflächenwirksamen Schuhpflegemitteln. " +
                "Vorläufer der Schuhcreme war bis zu Beginn des zwanzigsten Jahrhunderts die Schuhwichse.\n" +
                "Für die Mainzer Wachswarenfabrik Werner und Mertz entwickelte der Chemiker Philipp Adam Schneider die erste moderne Schuhcreme der Welt, für die mit Wirkung ab dem Jahr 1901 ein Patent in Deutschland erteilt wurde. " +
                "Sie löste die Schuhwichse ab.";

        TextSimilarity sim = new TextSimilarity(text1, text2);
        assertThat(String.format("Should be less than 0.8 but was %f.", sim.getDiceCoefficientSimilarity()), sim.getDiceCoefficientSimilarity(), lessThan(0.8));
        System.out.println(sim.getDiceCoefficientSimilarity());

    }
}
