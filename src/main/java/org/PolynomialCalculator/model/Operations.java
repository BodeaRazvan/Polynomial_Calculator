package org.PolynomialCalculator.model;

public class Operations {
    public boolean divisionByZero=false;
    public boolean degreeError=false;

    public Polynomial opAddition(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();
        for (Monomial monomial1 : polynomial1.getMonomials()) {
            for (Monomial monomial2 : polynomial2.getMonomials()) {
                if (monomial1.getExponent() == monomial2.getExponent()) {
                    monomial1.setAdded(true);
                    monomial2.setAdded(true);
                    Monomial additionMonomial = new Monomial(monomial1.getCoefficient() + monomial2.getCoefficient(), monomial1.getExponent());
                    result.add(additionMonomial);
                }
            }
        }
        for (Monomial monomial1 : polynomial1.getMonomials()) {
            if (!monomial1.isAdded()) {
                result.add(monomial1);
            }
        }
        for (Monomial monomial2 : polynomial2.getMonomials()) {
            if (!monomial2.isAdded()) {
                result.add(monomial2);
            }
        }
        return result;
    }

    public Polynomial opSubtraction(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();
        for (Monomial monomial1 : polynomial1.getMonomials()) {
            for (Monomial monomial2 : polynomial2.getMonomials()) {
                if (monomial1.getExponent() == monomial2.getExponent()) {
                    monomial1.setAdded(true);
                    monomial2.setAdded(true);
                    Monomial additionMonomial = new Monomial(monomial1.getCoefficient() - monomial2.getCoefficient(), monomial1.getExponent());
                    result.add(additionMonomial);
                }
            }
        }
        for (Monomial monomial1 : polynomial1.getMonomials()) {
            if (!monomial1.isAdded()) {
                result.add(monomial1);
            }
        }
        for (Monomial monomial2 : polynomial2.getMonomials()) {
            if (!monomial2.isAdded()) {
                monomial2.setCoefficient(0 - monomial2.getCoefficient());
                result.add(monomial2);
            }
        }
        return result;
    }

    public Polynomial opMultiplication(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();
        for (Monomial monomial1 : polynomial1.getMonomials()) {
            for (Monomial monomial2 : polynomial2.getMonomials()) {
                if (monomial1.getExponent() == 0) {
                    result.add(new Monomial(monomial1.getCoefficient() * monomial2.getCoefficient(), monomial2.getExponent()));
                } else if (monomial2.getExponent() == 0) {
                    result.add(new Monomial(monomial1.getCoefficient() * monomial2.getCoefficient(), monomial1.getExponent()));
                } else {
                    result.add(new Monomial(monomial1.getCoefficient() * monomial2.getCoefficient(), monomial1.getExponent() + monomial2.getExponent()));
                }
            }
        }
        return result;
    }

    public Polynomial opDerivative(Polynomial polynomial) {
        Polynomial result = new Polynomial();
        for (Monomial monomial : polynomial.getMonomials()) {
            monomial.setCoefficient(monomial.getCoefficient() * monomial.getExponent());
            monomial.setExponent(monomial.getExponent() - 1);
            result.add(monomial);
        }
        return result;
    }

    public Polynomial opIntegration(Polynomial polynomial) {
        Polynomial result = new Polynomial();
        for (Monomial monomial : polynomial.getMonomials()) {
            monomial.setCoefficient(monomial.getCoefficient() / (monomial.getExponent() + 1));
            monomial.setExponent(monomial.getExponent() + 1);
            result.add(monomial);
        }
        return result;
    }

    public Polynomial[] opDivision(Polynomial polynomial1, Polynomial polynomial2) {
        //this method is over 30 lines but it contains a lot of exception handling
        Polynomial quotient=new Polynomial();
        polynomial1.orderPolynomial(polynomial1);
        polynomial2.orderPolynomial(polynomial2);
        Polynomial copyPolynomial1=polynomial1.copyPolynomial(polynomial1);
        Polynomial auxPolynomial ;
        Polynomial multiplicationPolynomial=new Polynomial();
        //error handling
        Polynomial wrongInputPolynomial=new Polynomial();
        wrongInputPolynomial.add(new Monomial(0,0));
        Polynomial[] wrongInput = new Polynomial[2];
        wrongInput[0]=wrongInputPolynomial;
        wrongInput[1]=wrongInputPolynomial;
        if(polynomial1.getMonomials().get(0).getExponent()<polynomial2.getMonomials().get(0).getExponent()){
            degreeError=true;
            return wrongInput;
        }
        if( (polynomial2.getMonomials().get(0).getCoefficient()==0 && polynomial2.getMonomials().size()==1)){
            divisionByZero=true;
            return wrongInput;
        }
        // end of error handling

        int i=0;
        if(polynomial2.getMonomials().get(0).getExponent()==0 && polynomial1.getMonomials().size()==1){
            quotient.add(new Monomial(polynomial1.getMonomials().get(0).getCoefficient()/polynomial2.getMonomials().get(0).getCoefficient(),0));
            copyPolynomial1.getMonomials().removeAll(copyPolynomial1.getMonomials());
            copyPolynomial1.getMonomials().add(new Monomial(0,0));
        }else
       while(copyPolynomial1.getMonomials().get(0).getExponent()>=polynomial2.getMonomials().get(0).getExponent()){
            //This instruction looks complicated but it's just ( coefficient1/coefficient2 , exponent1-exponent2)
                quotient.add(new Monomial(copyPolynomial1.getMonomials().get(0).getCoefficient() / polynomial2.getMonomials().get(0).getCoefficient(), copyPolynomial1.getMonomials().get(0).getExponent() - polynomial2.getMonomials().get(0).getExponent()));

                multiplicationPolynomial.add(quotient.getMonomials().get(i));
                auxPolynomial = opMultiplication(multiplicationPolynomial, polynomial2);
                auxPolynomial = auxPolynomial.innerAdd(auxPolynomial);
                auxPolynomial.orderPolynomial(auxPolynomial);
                auxPolynomial = auxPolynomial.removeZeroes(auxPolynomial);

                copyPolynomial1 = opSubtraction(copyPolynomial1, auxPolynomial);
                copyPolynomial1 = copyPolynomial1.innerAdd(copyPolynomial1);
                copyPolynomial1 = copyPolynomial1.removeZeroes(copyPolynomial1);
                copyPolynomial1.orderPolynomial(copyPolynomial1);

                multiplicationPolynomial.getMonomials().remove(0);
                i++;
           }
        Polynomial[] returnedPolinom = new Polynomial[2];
        returnedPolinom[0] = quotient;
        returnedPolinom[1] = copyPolynomial1;
        return returnedPolinom;
    }
}
