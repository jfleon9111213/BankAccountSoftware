/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3560project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Me
 */
public class CreditCardHandler extends BaseHandler{
    private Label currentCredit;
    private Label dollarSign;
    private Label cent;
    private Label balanceOwed;
    
    private TextField dollarValue;
    private TextField centValue;
    
    private Button payAmount;
    private Button payAll;
    private Button back;
    
    private HBox fieldsH;
    private HBox choicesH;
    private VBox rootNode;
    
    private Stage primaryStage;
    
    public CreditCardHandler() {
        super.sceneNumber = 5;
    }
    
    public void handleStage(Stage primaryStage, int sceneNumber) {
        if (sceneNumber == super.sceneNumber) {
            this.primaryStage = primaryStage;
            start(primaryStage);
        } 
        else if (successor != null && sceneNumber > super.sceneNumber) {
            successor.handleStage(primaryStage, sceneNumber);
        }
        else if (predecessor != null && sceneNumber < super.sceneNumber) {
            predecessor.handleStage(primaryStage, sceneNumber);
        }
        else {
            System.exit(0);
        }
    }
    
    private void start(Stage primaryStage) {
        createLabels();
        createTextFields();
        createButtons();
        createLayout();
        
        Scene scene = new Scene(rootNode, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Credit Card");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private void createLabels() {
        currentCredit = new Label("$(DOLLARS).(CENTS) Available");
        balanceOwed = new Label("$ (DOLLARS).(CENTS) Owe");
        dollarSign = new Label("$");
        cent = new Label(".");
    }
    
    private void createTextFields() {
        dollarValue = new TextField();
        centValue = new TextField();
        
        centValue.setMaxWidth(30);
        dollarValue.setMaxWidth(75);
        
        dollarValue.textProperty().addListener((source, oldValue, newValue) -> {
            if(!(newValue.matches("^[0-9]*$"))) {
                dollarValue.setText(oldValue);
            }
        });
        
        centValue.textProperty().addListener((source, oldValue, newValue) -> {
            if(newValue.length() > 2 || !(newValue.matches("^[0-9]*$"))) {
                centValue.setText(oldValue);
            }
        });
    }
    
    private void createButtons() {
        payAmount = new Button("Pay Inserted Amount");
        payAll = new Button("Pay All");
        back = new Button("Back");
        
        back.setOnAction(event -> {
            handleStage(primaryStage, 2);
        });
        
        payAmount.setOnAction(event -> {
            if(dollarValue.getText().isEmpty() || centValue.getText().length() < 2) {
                Alert fieldError = new Alert(Alert.AlertType.ERROR, "Field was not fully filled out. Dollar must have at least one digit and"
                        + " cents must have at least two digits");
                fieldError.setHeaderText("Deposit failed.");
                fieldError.show();
            }
            else if (false) {//overpaying
                Alert overError = new Alert(Alert.AlertType.WARNING, "Only balance owed will be payed off.");
                overError.setHeaderText("Not all money was accepted");
                overError.show();
            }
            else {
                //Pay balance
            }
        });
        
        payAll.setOnAction(event -> {
            //balance payed off
        });
    }
    
    private void createLayout() {
        fieldsH = new HBox(dollarSign, dollarValue, cent, centValue);
        fieldsH.setAlignment(Pos.CENTER);
        choicesH = new HBox(30, payAll, payAmount);
        choicesH.setAlignment(Pos.CENTER);
        rootNode = new VBox(20, currentCredit, balanceOwed, fieldsH, choicesH, back);
        rootNode.setAlignment(Pos.CENTER);
    }
    
}
