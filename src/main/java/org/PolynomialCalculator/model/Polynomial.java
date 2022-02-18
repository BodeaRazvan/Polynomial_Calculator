package org.PolynomialCalculator.model;

import java.util.*;

public class Polynomial {
    private final Monomial monomial = new Monomial();
    private ArrayList<Monomial> monomials =new ArrayList<>();

    public Polynomial(ArrayList<Monomial> monomials) {
        this.monomials=monomials;
    }
    public Polynomial(){
        monomials = new ArrayList<Monomial>();
    }
    public Polynomial(int degree){
        monomials=new ArrayList<Monomial>(degree);
        for(int i=0;i<=degree;i++){
            monomials.add(new Monomial(i,0));
        }
    }

    public List<Monomial> getMonomials() {
        return this.monomials;
    }

    public void add(Monomial a) {
        this.monomials.add(a);
    }

    public Polynomial copyPolynomial(Polynomial polynomial){
        Polynomial result = new Polynomial();
        for(Monomial monomial:polynomial.getMonomials()){
            result.add(new Monomial(monomial.getCoefficient(),monomial.getExponent()));
        }
        return result;
    }

    public void orderPolynomial(Polynomial polynomial){   //order the monomials of a polynomial in ascending order
        for(Monomial monomial1: polynomial.getMonomials()){
            for(Monomial monomial2: polynomial.getMonomials()){
                if(monomial1.getExponent()>monomial2.getExponent()){
                    int temp1=monomial1.getExponent();
                    monomial1.setExponent(monomial2.getExponent());
                    monomial2.setExponent(temp1);
                    double temp2=monomial1.getCoefficient();
                    monomial1.setCoefficient(monomial2.getCoefficient());
                    monomial2.setCoefficient(temp2);
                }
            }
        }
    }

    public Polynomial innerAdd(Polynomial polynomial){ //Add the monomials with the same power inside a Polynomial
        Polynomial result = new Polynomial();

        for(Monomial monomial1:polynomial.getMonomials()){
            int i=-1;
            for(int j=0;j<result.getMonomials().size();j++){  //I had to use a for j=0;j++ here because i need the position of the monomial
                if(result.getMonomials().get(j).getExponent()==monomial1.getExponent()){
                    i=j;
                }
            }
            if(i!=-1){
                result.getMonomials().get(i).setCoefficient(result.getMonomials().get(i).getCoefficient()+monomial1.getCoefficient());
            }else{
                result.add(monomial1);
            }
        }
        return result;
    }

    public Polynomial removeZeroes(Polynomial polynomial) {
         Polynomial result=new Polynomial();
         for(Monomial monomial:polynomial.getMonomials()){
             if((int)monomial.getCoefficient()!=0){
                 result.add(monomial);
             }
         }
         if(result.getMonomials().size()==0){
             result.add(new Monomial(0,0));
         }
         return result;
    }


}
