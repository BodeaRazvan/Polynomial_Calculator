package org.PolynomialCalculator.model;


public class Monomial {
    private double coefficient;
    private int exponent;
    private boolean added=false;

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public Monomial() {
        super();
        this.coefficient = 0;
        this.exponent = 0;
    }

    public Monomial(double coefficient, int exponent){
        super();
        this.coefficient=coefficient;
        this.exponent=exponent;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public String toString() {  //this method exceeds the 30 line rule but it is straight forward and cannot be split into more methods that would make sense
        if (coefficient == 0)
            return "+0";
        if (coefficient == 1 && exponent != 0)
            return "+x^" + exponent;
        if (exponent == 1)
            if(coefficient>0)
                if((int)coefficient==coefficient)
                     return "+" + (int)coefficient + "x";
                else
                     return "+" + String.format("%.2f",coefficient) +"x";
            else
                if((int)coefficient==coefficient)
                     return (int)coefficient + "x";
                else
                    return  String.format("%.2f",coefficient) + "x";
        if (exponent == 0)
            if(coefficient>0)
                if((int)coefficient==coefficient)
                     return "+" + (int)coefficient;
                else
                     return "+" + String.format("%.2f",coefficient);
            else
                 if((int)coefficient==coefficient)
                     return "" + (int)coefficient;
                 else
                     return "" + String.format("%.2f",coefficient);
        if (coefficient == -1)
            return "-x^" + exponent;
        if(coefficient>0)
            if((int)coefficient==coefficient)
                return "+" + (int)coefficient + "x^" + exponent;
            else
                return "+" + String.format("%.2f",coefficient) + "x^" +exponent;
        else
            if((int)coefficient==coefficient)
                return (int)coefficient + "x^" + exponent;
            else
                return coefficient+ "x^" +exponent;
    }

}
