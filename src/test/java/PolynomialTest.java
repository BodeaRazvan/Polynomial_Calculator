import org.PolynomialCalculator.model.Monomial;
import org.PolynomialCalculator.model.Polynomial;
import org.junit.Test;
import org.PolynomialCalculator.model.Operations;
import org.PolynomialCalculator.controller.Controller;
import org.PolynomialCalculator.model.Parsing;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class PolynomialTest {

    @Test
    public void testCopyPolynomial(){
        Polynomial polynomial1=new Polynomial();
        polynomial1.add(new Monomial(3,2));
        polynomial1.add(new Monomial(2,1));
        polynomial1.add(new Monomial(4,4));

        Polynomial copy= new Polynomial();
        copy=polynomial1.copyPolynomial(polynomial1);
        assertEquals(polynomial1.getMonomials().toString(), copy.getMonomials().toString());
    }

    @Test
    public void  testOrderPolynomial(){
        Polynomial polynomial1 =new Polynomial();
        polynomial1.add(new Monomial(3,2));
        polynomial1.add(new Monomial(2,1));
        polynomial1.add(new Monomial(4,4));
        polynomial1.add(new Monomial(1,0));
        //+3x^2+2x+4x^4+1

        polynomial1.orderPolynomial(polynomial1);
        System.out.println(polynomial1.getMonomials());
        assertEquals("[+4x^4, +3x^2, +2x, +1]",polynomial1.getMonomials().toString());
    }

    @Test
    public void testInnerAdd(){
        Polynomial polynomial1=new Polynomial();
        polynomial1.add(new Monomial(3,3));
        polynomial1.add(new Monomial(4,3));
        polynomial1.add(new Monomial(6,3));
        polynomial1.add(new Monomial(4,4));
        polynomial1.add(new Monomial(4,4));

        polynomial1=polynomial1.innerAdd(polynomial1);
        polynomial1.orderPolynomial(polynomial1);
        assertEquals("[+8x^4, +13x^3]",polynomial1.getMonomials().toString());
    }

    @Test
    public void testRemoveZeroes(){
        Polynomial polynomial1=new Polynomial();
        polynomial1.add(new Monomial(3,3));
        polynomial1.add(new Monomial(0,0));
        polynomial1.add(new Monomial(0,0));
        polynomial1.add(new Monomial(0,0));
        polynomial1.add(new Monomial(0,0));


        polynomial1=polynomial1.removeZeroes(polynomial1);
        assertEquals("[+3x^3]",polynomial1.getMonomials().toString());
    }
}
