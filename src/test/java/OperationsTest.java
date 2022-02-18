import org.PolynomialCalculator.model.Monomial;
import org.PolynomialCalculator.model.Polynomial;
import org.junit.Test;
import org.PolynomialCalculator.model.Operations;
import org.PolynomialCalculator.controller.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OperationsTest {

    Operations operation =new Operations();
    Polynomial testPolynomial;

    Monomial monomial1 = new Monomial(1,1);     // x
    Monomial monomial2 = new Monomial(2,2);     // 2x^2
    Monomial monomial3 = new Monomial(4,3);     // 4x^3
    Monomial monomial4 = new Monomial(-4,2);    // -4x^2
    Monomial monomial5 = new Monomial(-2,1);    // -2x
    Monomial monomial6 = new Monomial(3,0);     // 3

    public Polynomial createPolynomial1(){ // 4x^3+2x^2+x
        Polynomial polynomial1=new Polynomial();
        polynomial1.add(monomial3);
        polynomial1.add(monomial2);
        polynomial1.add(monomial1);
        return  polynomial1;
    }
    public Polynomial createPolynomial2(){ // 4x^3-4x^2
        Polynomial polynomial2=new Polynomial();
        polynomial2.add(monomial3);
        polynomial2.add(monomial4);
       return polynomial2;
    }
    public Polynomial createPolynomial3(){ // 2x^2-2x+3
        Polynomial polynomial3=new Polynomial();
        polynomial3.add(monomial2);
        polynomial3.add(monomial5);
        polynomial3.add(monomial6);
        return polynomial3;
    }

    //The operations tested will not give the output in the correct order of exponent degree (this is done by another method)
    @Test
    public void additionTest1(){
        Polynomial polynomial1=createPolynomial1();

        testPolynomial=operation.opAddition(polynomial1,polynomial1);
        assertEquals("[+8x^3, +4x^2, +2x]", testPolynomial.getMonomials().toString());   //I have to give the result in this form because the toString is called on each monomial
    }

    @Test
    public void additionTest2(){
        Polynomial polynomial2 = createPolynomial2();
        Polynomial polynomial3 = createPolynomial3();

        testPolynomial=operation.opAddition(polynomial2,polynomial3);
        assertEquals("[-2x^2, +4x^3, -2x, +3]", testPolynomial.getMonomials().toString());
    }

    @Test
    public void subtractionTest1(){
        Polynomial polynomial1 = createPolynomial1();
        Polynomial polynomial2 = createPolynomial2();

        testPolynomial=operation.opSubtraction(polynomial1,polynomial2);
        assertEquals("[+0, +6x^2, +x^1]", testPolynomial.getMonomials().toString());
    }

    @Test
    public void subtractionTest2(){
        Polynomial polynomial1 = createPolynomial1();
        Polynomial polynomial3 = createPolynomial3();

        testPolynomial=operation.opSubtraction(polynomial1,polynomial3);
        assertEquals("[+0, +3x, +4x^3, -3]", testPolynomial.getMonomials().toString());
    }

    @Test
    public void multiplicationTest1(){
        Polynomial polynomial1 = createPolynomial1();
        Polynomial polynomial2 = createPolynomial2();

        testPolynomial=operation.opMultiplication(polynomial1,polynomial2);
        assertEquals("[+16x^6, -16x^5, +8x^5, -8x^4, +4x^4, -4x^3]", testPolynomial.getMonomials().toString());
    }

    @Test
    public void multiplicationTest2(){
        Polynomial polynomial1 = createPolynomial1();
        Polynomial polynomial3 = createPolynomial3();

        testPolynomial=operation.opMultiplication(polynomial1,polynomial3);
        assertEquals("[+8x^5, -8x^4, +12x^3, +4x^4, -4x^3, +6x^2, +2x^3, -2x^2, +3x]", testPolynomial.getMonomials().toString());
    }

    @Test
    public void divisionTest1(){
        Polynomial polynomial1=new Polynomial();
        Polynomial polynomial2=new Polynomial();
        polynomial1.add(new Monomial(1,3));
        polynomial1.add(new Monomial(-2,2));
        polynomial1.add(new Monomial(6,1));
        polynomial1.add(new Monomial(-5,0));

        polynomial2.add(new Monomial(1,2));
        polynomial2.add(new Monomial(-1,0));

        Polynomial quotient=operation.opDivision(polynomial1,polynomial2)[0];
        Polynomial remainder=operation.opDivision(polynomial1,polynomial2)[1];

        assertEquals("[+x^1, -2]",quotient.getMonomials().toString());
        assertEquals("[+7x, -7]",remainder.getMonomials().toString());
    }

    @Test
    public void differentiationTest1(){
        Polynomial polynomial1 = createPolynomial1();

        testPolynomial=operation.opDerivative(polynomial1);
        assertEquals("[+12x^2, +4x, +1]", testPolynomial.getMonomials().toString());
    }

    @Test
    public void differentiationTest2(){
        Polynomial polynomial3 = createPolynomial3();

        testPolynomial=operation.opDerivative(polynomial3);
        assertEquals("[+4x, -2, +0]", testPolynomial.getMonomials().toString());
    }

    @Test
    public void integrationTest1(){
        Polynomial polynomial1 = createPolynomial1();

        testPolynomial=operation.opIntegration(polynomial1);
        assertEquals("[+x^4, +0.67x^3, +0.50x^2]", testPolynomial.getMonomials().toString());

    }

    @Test
    public void integrationTest2(){
        Polynomial polynomial3 = createPolynomial3();

        testPolynomial=operation.opIntegration(polynomial3);
        assertEquals("[+0.67x^3, -x^2, +3x]", testPolynomial.getMonomials().toString());
    }
}
