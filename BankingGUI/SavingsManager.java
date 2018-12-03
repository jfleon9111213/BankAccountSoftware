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
public class SavingsManager extends BaseHandler{
    private Label currentMoney;
    private Label dollarSign;
    private Label cent;
    
    private TextField dollarValue;
    private TextField centValue;
    
    private Button deposit;
    private Button withdraw;
    private Button back;
    
    private HBox fieldsH;
    private HBox choicesH;
    private VBox rootNode;
    
    private Stage primaryStage;
    
    public SavingsManager() {
        super.sceneNumber = 3;
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
        primaryStage.setTitle("Savings Account");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private void createLabels() {
        currentMoney = new Label("$(DOLLARS).(CENTS)");
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
        deposit = new Button("Deposit");
        withdraw = new Button("Withdraw");
        back = new Button("Back");
        
        back.setOnAction(event -> {
            handleStage(primaryStage, 2);
        });
        
        deposit.setOnAction(event -> {
            if(dollarValue.getText().isEmpty() || centValue.getText().length() < 2) {
                Alert fieldError = new Alert(Alert.AlertType.ERROR, "Field was not fully filled out. Dollar must have at least one digit and"
                        + " cents must have at least two digits");
                fieldError.setHeaderText("Deposit failed.");
                fieldError.show();
            }
            else {
                //deposit money
            }
        });
        
        withdraw.setOnAction(event -> {
            if(dollarValue.getText().isEmpty() || centValue.getText().length() < 2) {
                Alert fieldError = new Alert(Alert.AlertType.ERROR, "Field was not fully filled out. Dollar must have at least one digit and"
                        + " cents must have at least two digits");
                fieldError.setHeaderText("Deposit failed.");
                fieldError.show();
            }
            else if (false) { //not enough money
                Alert withdrawError = new Alert(Alert.AlertType.ERROR, "Not enough money to withdraw that amount.");
                withdrawError.setHeaderText("Withdraw failed.");
                withdrawError.show();
            }
            else {
                //withdraw money
            }
        });
        
    }
    
    private void createLayout() {
        fieldsH = new HBox(dollarSign, dollarValue, cent, centValue);
        fieldsH.setAlignment(Pos.CENTER);
        choicesH = new HBox(30, deposit, withdraw);
        choicesH.setAlignment(Pos.CENTER);
        rootNode = new VBox(20, currentMoney, fieldsH, choicesH, back);
        rootNode.setAlignment(Pos.CENTER);
    }
    
}
