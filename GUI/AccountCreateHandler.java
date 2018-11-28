/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3560project;

/**
 *
 * @author Me
 */

import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;

public class AccountCreateHandler extends BaseHandler{
    private Label firstName;
    private Label lastName;
    private Label birth;
    private Label userName;
    private Label passWord;
    private Label pin;
    
    private TextField textFirst;
    private TextField textLast;
    private TextField textUser;
    private TextField textPass;
    private TextField textPin;
    
    private ChoiceBox<Integer> choiceMonth;
    private ChoiceBox<Integer> choiceDay;
    private ChoiceBox<Integer> choiceYear;
    
    private Button home;
    private Button signUp;
    
    private HBox hboxNames;
    private HBox hboxNamesText;
    private HBox hboxBirth;
    private HBox hboxButtons;
    private VBox vboxRoot;
    
    private Stage primaryStage;
    
    public AccountCreateHandler() {
        super.sceneNumber = 1;
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
        createFields();
        createChoiceBoxes();
        createButtons();
        createLayout();
        
        Scene scene = new Scene(vboxRoot, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Create An Account");
        
        primaryStage.show();
        
    }
    
    private void createLabels() {
        firstName = new Label("First Name");
        lastName = new Label("Last Name");
        birth = new Label("Date of Birth: MM/DD/YY");
        userName = new Label("Username");
        passWord = new Label("Password");
        pin = new Label("Choice of PIN");
    }
    
    private void createFields() {
        textFirst = new TextField();
        textFirst.setMaxWidth(100);
        textLast = new TextField();
        textLast.setMaxWidth(100);
        textUser = new TextField();
        textUser.setMaxWidth(100);
        textPass = new TextField();
        textPass.setMaxWidth(100);
        textPin = new TextField();
        textPin.setMaxWidth(50);
    }
    
    private void createChoiceBoxes() {
        choiceMonth = new ChoiceBox<>();
        choiceDay = new ChoiceBox<>();
        choiceYear = new ChoiceBox<>();
        
        for(int i = 2018; i >= 1900; i--) {
            choiceYear.getItems().add(i);
        }
        
        for(int i = 1; i <= 12; i++) {
            choiceMonth.getItems().add(i);
        }
        
        for(int i = 1; i <= 31; i++) {
            choiceDay.getItems().add(i);
        }
        
        choiceMonth.setOnAction(event -> {
            switch(choiceMonth.getValue()) {
                case 2:
                    choiceDay.getItems().removeAll( 29, 30, 31);
                    break;
                case 4: case 6: case 9: case 11:
                    if(!choiceDay.getItems().contains( 29)) {
                        choiceDay.getItems().add(29);
                    }
                    if(!choiceDay.getItems().contains(30)) {
                        choiceDay.getItems().add( 30);
                    }
                    choiceDay.getItems().removeAll(31);
                    break;
                default:
                    for(int i = 29; i <= 31; i++) {
                        if(!choiceDay.getItems().contains(i)) {
                            choiceDay.getItems().add(i);
                        }
                    }
            }
        });
        
    }
    
    private void createButtons() {
        home = new Button("Home");
        signUp = new Button("Sign Up");
        
        home.setOnAction(event -> {
            this.handleStage(primaryStage, 0);
        });
        
        signUp.setOnAction(event -> {
            if (! (textPin.getText().matches("[0-9]+") && textPin.getText().length() == 4)) {
                System.out.println("Error");
            }
            //Insert creating account code here
        });
    }
    
    private void createLayout() {
        hboxNames = new HBox(52, firstName, lastName);
        hboxNames.setAlignment(Pos.CENTER);
        hboxNamesText = new HBox(10, textFirst, textLast);
        hboxNamesText.setAlignment(Pos.CENTER);
        hboxBirth = new HBox(choiceMonth, choiceDay, choiceYear);
        hboxBirth.setAlignment(Pos.CENTER);
        hboxButtons = new HBox(30, home, signUp);
        hboxButtons.setAlignment(Pos.CENTER);
        vboxRoot = new VBox(10, hboxNames, hboxNamesText, birth, hboxBirth, userName, textUser, passWord, textPass, pin, textPin, hboxButtons);
        vboxRoot.setAlignment(Pos.CENTER);
    }   
}
