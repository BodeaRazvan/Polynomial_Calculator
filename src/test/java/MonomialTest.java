import org.PolynomialCalculator.model.Monomial;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MonomialTest {

    @Test
    public void toStringTest1(){
        Monomial monomial = new Monomial(3,4);  //+3x^4
        assertEquals("+3x^4",monomial.toString());
    }
    @Test
    public void toStringTest2(){
        Monomial monomial=new Monomial(23,14);  //+23x^14
        assertEquals("+23x^14",monomial.toString());
    }
    @Test
    public void toStringTest3(){
        Monomial monomial=new Monomial(-5,3);  //-5x^3
        assertEquals("-5x^3",monomial.toString());
    }
}
