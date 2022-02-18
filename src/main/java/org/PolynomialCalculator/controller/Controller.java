package org.PolynomialCalculator.controller;

import javafx.scene.paint.Color;
import org.PolynomialCalculator.model.Monomial;
import org.PolynomialCalculator.model.Operations;
import org.PolynomialCalculator.model.Parsing;
import org.PolynomialCalculator.model.Polynomial;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller extends Operations {
    Operations operation = new Operations();
    Polynomial polynomialFunctions = new Polynomial();
    @FXML private TextField Poly1;
    @FXML private TextField Poly2;
    @FXML private TextField Result;
    private boolean SelectedField1;
    private boolean SelectedField2;
    private final Parsing parse = new Parsing();

    public void setResult(String result) {
        Result.setText(result);
    }
    @FXML
    private String getPolynomial1() throws IOException{
        return Poly1.getText();
    }
    @FXML
    private String getPolynomial2() throws IOException{
        return Poly2.getText();
    }

    @FXML
    private void opSelectedAddition() throws IOException{
        Polynomial polynomial1,polynomial2,result;
        polynomial1 = new Polynomial(parse.parsePolynomial(getPolynomial1()));
        polynomial2 = new Polynomial(parse.parsePolynomial(getPolynomial2()));
        polynomial1 = polynomialFunctions.innerAdd(polynomial1);
        polynomial2 = polynomialFunctions.innerAdd(polynomial2);

        result = opAddition(polynomial1,polynomial2);
        polynomialFunctions.orderPolynomial(result);
        result=polynomialFunctions.removeZeroes(result);
        result=polynomialFunctions.innerAdd(result);

        StringBuilder rez = new StringBuilder();
        for(Monomial monomial: result.getMonomials()) {
            rez.append(monomial.toString());
        }
        setResult(rez.toString());

        if(parse.error){
            setResult("Wrong input, please try again!");
            parse.error=false;
        }

    }
    @FXML
    private void opSelectedSubtraction() throws IOException{
        Polynomial polynomial1,polynomial2,result;
        polynomial1 = new Polynomial(parse.parsePolynomial(getPolynomial1()));
        polynomial2 = new Polynomial(parse.parsePolynomial(getPolynomial2()));
        polynomial1 = polynomialFunctions.innerAdd(polynomial1);
        polynomial2 = polynomialFunctions.innerAdd(polynomial2);

        result = opSubtraction(polynomial1,polynomial2);
        polynomialFunctions.orderPolynomial(result);
        result=polynomialFunctions.removeZeroes(result);
        result=polynomialFunctions.innerAdd(result);

        StringBuilder rez = new StringBuilder();
        for(Monomial monomial: result.getMonomials()) {
            rez.append(monomial.toString());
        }
        setResult(rez.toString());

        if(parse.error){
            setResult("Wrong input, please try again!");
            parse.error=false;
        }
    }
    @FXML
    private void opSelectedMultiplication() throws IOException{
        Polynomial polynomial1,polynomial2,result;
        polynomial1 = new Polynomial(parse.parsePolynomial(getPolynomial1()));
        polynomial2 = new Polynomial(parse.parsePolynomial(getPolynomial2()));
        polynomial1 = polynomialFunctions.innerAdd(polynomial1);
        polynomial2 = polynomialFunctions.innerAdd(polynomial2);

        result = opMultiplication(polynomial1,polynomial2);
        polynomialFunctions.orderPolynomial(result);
        result=polynomialFunctions.removeZeroes(result);
        result=polynomialFunctions.innerAdd(result);

        StringBuilder rez = new StringBuilder();
        for(Monomial monomial: result.getMonomials()) {
            rez.append(monomial.toString());
        }
        setResult(rez.toString());

        if(parse.error){
            setResult("Wrong input, please try again!");
            parse.error=false;
        }
    }
    @FXML
    private void opSelectedDivision() throws IOException{
        //Division is not fully functional but it works for a lot of cases
        Polynomial polynomial1,polynomial2;
        Polynomial remainder;
        Polynomial quotient;
        polynomial1 = new Polynomial(parse.parsePolynomial(getPolynomial1()));
        polynomial2 = new Polynomial(parse.parsePolynomial(getPolynomial2()));
        if(polynomial1.getMonomials().size()==0 || polynomial2.getMonomials().size()==0){return;}
        if(parse.error){
            Result.clear();
            setResult("Wrong input, please try again!");
            parse.error=false;
            return;
        }
        polynomial1 = polynomialFunctions.innerAdd(polynomial1);
        polynomial2 = polynomialFunctions.innerAdd(polynomial2);

        quotient  = opDivision(polynomial1,polynomial2)[0];
        remainder = opDivision(polynomial1,polynomial2)[1];
        if(degreeError){
            Result.clear();
            setResult("ERROR: Degree of polynomial 2 > Degree of polynomial1");
            degreeError=false;
            return;
        }
        if(divisionByZero){
            Result.clear();
            setResult("ERROR: Division by ZERO");
            divisionByZero=false;
            return;
        }

        StringBuilder rez1 = new StringBuilder();
        for(Monomial monomial: quotient.getMonomials()) {
            rez1.append(monomial.toString());
        }

        StringBuilder rez2 = new StringBuilder();
        for(Monomial monomial: remainder.getMonomials()) {
             rez2.append(monomial.toString());
        }

        Result.setText("Quotient=");
        Result.appendText(rez1.toString());
        Result.appendText("    ");
        Result.appendText("Remainder=");
        Result.appendText(rez2.toString());
    }
    @FXML
    private void opSelectedDerivative() throws IOException{
        Polynomial polynomial1,result = null;
        polynomial1 = new Polynomial(parse.parsePolynomial(getPolynomial1()));
        polynomial1 = polynomialFunctions.innerAdd(polynomial1);

        result = opDerivative(polynomial1);
        polynomialFunctions.orderPolynomial(result);
        result=polynomialFunctions.removeZeroes(result);
        result=polynomialFunctions.innerAdd(result);

        StringBuilder rez = new StringBuilder();
        for(Monomial monomial: result.getMonomials()) {
            rez.append(monomial.toString());
        }
        setResult(rez.toString());

        if(parse.error){
            setResult("Wrong input, please try again!");
            parse.error=false;
        }
    }
    @FXML
    private void opSelectedIntegration() throws IOException{
        Polynomial polynomial1,result;
        polynomial1 = new Polynomial(parse.parsePolynomial(getPolynomial1()));
        polynomial1 = polynomialFunctions.innerAdd(polynomial1);

        result = opIntegration(polynomial1);
        polynomialFunctions.orderPolynomial(result);
        result=polynomialFunctions.removeZeroes(result);
        result=polynomialFunctions.innerAdd(result);

        StringBuilder rez = new StringBuilder();
        for(Monomial monomial: result.getMonomials()) {
            rez.append(monomial.toString());
        }
        setResult(rez.toString());

        if(parse.error){
            setResult("Wrong input, please try again!");
            parse.error=false;
        }
    }

    @FXML
    private void textFieldSelected1(){
        SelectedField1=true;
        SelectedField2=false;
    }

    @FXML
    private void textFieldSelected2(){
        SelectedField1=false;
        SelectedField2=true;
    }

    @FXML
    private void processButtons(ActionEvent actionEvent){
        String value=((Button)actionEvent.getSource()).getText();
        if(SelectedField1){
            Poly1.setText(Poly1.getText()+value);
        }
        if(SelectedField2){
            Poly2.appendText(value);
        }
    }

    @FXML
    private void Clear(){
        if(SelectedField1){
            Poly1.clear();
        }
        if(SelectedField2){
            Poly2.clear();
        }
    }

    // UI related code here
    @FXML
    private Button ButtonAdd;

    @FXML
    private void changeColorYellow1(){
        ButtonAdd.setStyle("-fx-background-color: #F59E00");
    }
    @FXML
    private void changeColorBlack1(){
        ButtonAdd.setStyle("-fx-background-color: #212121 ; -fx-border-color: #F59E00 " );
        ButtonAdd.setTextFill(Color.WHITE);
    }
    @FXML
    private Button ButtonSub;

    @FXML
    private void changeColorYellow2(){
        ButtonSub.setStyle("-fx-background-color: #F59E00");
    }
    @FXML
    private void changeColorBlack2(){
        ButtonSub.setStyle("-fx-background-color: #212121 ; -fx-border-color: #F59E00 " );
        ButtonSub.setTextFill(Color.WHITE);
    }
    @FXML
    private Button ButtonMul;

    @FXML
    private void changeColorYellow3(){
        ButtonMul.setStyle("-fx-background-color: #F59E00");
    }
    @FXML
    private void changeColorBlack3(){
        ButtonMul.setStyle("-fx-background-color: #212121 ; -fx-border-color: #F59E00 " );
        ButtonMul.setTextFill(Color.WHITE);
    }
    @FXML
    private Button ButtonDiv;

    @FXML
    private void changeColorYellow4(){
        ButtonDiv.setStyle("-fx-background-color: #F59E00");
    }
    @FXML
    private void changeColorBlack4(){
        ButtonDiv.setStyle("-fx-background-color: #212121 ; -fx-border-color: #F59E00 " );
        ButtonDiv.setTextFill(Color.WHITE);
    }
    @FXML
    private Button ButtonDif;

    @FXML
    private void changeColorYellow5(){
        ButtonDif.setStyle("-fx-background-color: #F59E00");
    }
    @FXML
    private void changeColorBlack5(){
        ButtonDif.setStyle("-fx-background-color: #212121 ; -fx-border-color: #F59E00 " );
        ButtonDif.setTextFill(Color.WHITE);
    }
    @FXML
    private Button ButtonIn;

    @FXML
    private void changeColorYellow6(){
        ButtonIn.setStyle("-fx-background-color: #F59E00");
    }
    @FXML
    private void changeColorBlack6(){
        ButtonIn.setStyle("-fx-background-color: #212121 ; -fx-border-color: #F59E00 " );
        ButtonIn.setTextFill(Color.WHITE);
    }
}