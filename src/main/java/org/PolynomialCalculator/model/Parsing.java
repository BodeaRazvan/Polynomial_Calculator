package org.PolynomialCalculator.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parsing {
    public boolean error=false;
    // credits for the regex pattern   https://stackoverflow.com/questions/28859919/java-regex-separate-degree-coeff-of-polynomial/44188848
    // I studied this pattern with regex101 to see how groups are created and understand how to parse the data
    Pattern pattern =Pattern.compile("(?:\\h*)([-+]?\\d*)(?:\\h*)[x](\\^(\\d+))?|(?:\\h*)([-+]?\\d+)",Pattern.CASE_INSENSITIVE);

    public ArrayList<Monomial> parsePolynomial(String Polynomial) throws IOException{
        Polynomial=Polynomial.replaceAll("\\s","");
        Matcher matcher =pattern.matcher(Polynomial);
        ArrayList<Monomial> newPolynomial = new ArrayList<Monomial>();
        validateInput(Polynomial);
        double coefficient = 0; int exponent = 0;
        while(matcher.find()){
            if (matcher.group(4)!=null){
                coefficient=Integer.parseInt(matcher.group(4));
                exponent=0;
            }else{
                if(matcher.group(2)!=null){
                    exponent=Integer.parseInt(matcher.group(3));
                }else{
                    exponent=1;
                }
                if(!matcher.group(1).equals("") && matcher.group(1)!=null){
                    if(matcher.group(1).equals("-")){
                        coefficient=-1;
                    }else if (matcher.group(1).equals("+")){
                        coefficient=1;
                    }else{
                        coefficient=Integer.parseInt(matcher.group(1));
                    }
                }else{
                    coefficient=1;
                }
            }
            newPolynomial.add(new Monomial(coefficient,exponent));
            // System.out.println(coefficient + " " + exponent);   //Just testing
        }
        return newPolynomial;
    }

    public void validateInput(String input) throws IOException{
        try {
         for (int i = 0; i < input.length(); i++){
             char c = input.charAt(i);
             if((Character.isLetter(c)&& c!='X' && c!='x')||(!Character.isDigit(c)&& !Character.isLetter(c)&& c!='+' && c!='-'&& c!='^')){
                 error=true;
                 throw new IllegalArgumentException("Invalid input!");
             }
         }
        } catch (IllegalArgumentException ex) {
            error=true;
        }
    }

}