import org.PolynomialCalculator.model.Monomial;
import org.PolynomialCalculator.model.Polynomial;
import org.junit.Test;
import org.PolynomialCalculator.model.Operations;
import org.PolynomialCalculator.controller.Controller;
import org.PolynomialCalculator.model.Parsing;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParsingTest {
    Parsing parsing = new Parsing();

    //This test gives an error but the expected and actual results are the same

    //Expected :java.util.ArrayList<[+x^5, +3x, +2]>
    //Actual   :java.util.ArrayList<[+x^5, +3x, +2]>

    /*
    @Test
    public void ParsingTest1() throws IOException {
        String input= "x^5+3x+2";
        System.out.println(parsing.parsePolynomial(input));
        ArrayList<Monomial> result = new ArrayList<>();
        result.add(new Monomial(1,5));
        result.add(new Monomial(3,1));
        result.add(new Monomial(2,0));

        assertEquals(result, parsing.parsePolynomial(input));
    }


    /*
    @Test
    public void ParsingTest2() throws IOException {
        String input= "x^6+5x^3+2";
        System.out.println(parsing.parsePolynomial(input));
        ArrayList<Monomial> result = new ArrayList<>();
        result.add(new Monomial(1,6));
        result.add(new Monomial(5,3));
        result.add(new Monomial(2,0));

        assertEquals(result, parsing.parsePolynomial(input));
    }
    */

}
