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
public class CreditCardApplicationHandler extends BaseHandler{
    
    private Label headerLabel;
    private Label dollarSign;
    private Label centSign;
    
    private TextField dollarField;
    private TextField centField;
    
    private Button applyButton;
    private Button back;
    
    private HBox buttonsH;
    private HBox fieldsH;
    private VBox rootNode;
    
    private Stage primaryStage;
    
    public CreditCardApplicationHandler() {
        super.sceneNumber = 6;
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
        
        Scene scene = new Scene(rootNode, 200, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Credit Card Application");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private void createLabels() {
        headerLabel = new Label("Enter security deposit amount:");
        dollarSign = new Label("$");
        centSign = new Label(".");
    }
    
    private void createTextFields() {
        dollarField = new TextField();
        centField = new TextField();
        
        centField.setMaxWidth(30);
        dollarField.setMaxWidth(75);
        
        dollarField.textProperty().addListener((source, oldValue, newValue) -> {
            if(!(newValue.matches("^[0-9]*$"))) {
                dollarField.setText(oldValue);
            }
        });
        
        centField.textProperty().addListener((source, oldValue, newValue) -> {
            if(newValue.length() > 2 || !(newValue.matches("^[0-9]*$"))) {
                centField.setText(oldValue);
            }
        });
    }
    
    private void createButtons() {
        applyButton = new Button("Apply");
        back = new Button("Back");
        
        back.setOnAction(event -> {
            handleStage(primaryStage, 2);
        });
        
        applyButton.setOnAction(event -> {
            if(dollarField.getText().isEmpty() || centField.getText().length() < 2) {
                Alert fieldError = new Alert(Alert.AlertType.ERROR, "Field was not fully filled out. Dollar must have at least one digit and"
                        + " cents must have at least two digits");
                fieldError.setHeaderText("Deposit failed.");
                fieldError.show();
            }
            else {
                //create credit card account
                Alert accountCreated = new Alert(Alert.AlertType.INFORMATION, "Credit card account created.");
                accountCreated.setHeaderText("Your security deposit has been accepted and your credit card account has been created");
                accountCreated.show();
                this.handleStage(primaryStage, 2);
            }
        });
    }
    
    private void createLayout() {
        fieldsH = new HBox(dollarSign, dollarField, centSign, centField);
        fieldsH.setAlignment(Pos.CENTER);
        buttonsH = new HBox(30, back, applyButton);
        buttonsH.setAlignment(Pos.CENTER);
        rootNode = new VBox(20, headerLabel, fieldsH, buttonsH);
        rootNode.setAlignment(Pos.CENTER);
    }    
}
