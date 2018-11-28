/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3560project;

import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.Scene;

/**
 *
 * @author Me
 */
public class LogInHandler extends BaseHandler{
    private Label user;
    private Label password;
    private Label pin;
    
    private TextField textUser;
    private PasswordField textPassword;
    private PasswordField textPin;
    
    private Button sign;
    private Button makeAccount;
    
    private VBox vbox;
    
    private Stage primaryStage;
    
    public LogInHandler() {
        super.sceneNumber = 0;
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
        createButtons();
        createVBox();
        
        Scene scene = new Scene(vbox, 210, 270);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Banking Application");
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }
    
    private void createLabels() {
        user = new Label("Enter Username:");
        password = new Label("Enter Your Password:");
        pin = new Label("Enter Your PIN Number:");
        
    }
    
    private void createFields() {
        textUser = new TextField();
        textPassword = new PasswordField();
        textPin = new PasswordField();
        
        textUser.setMaxWidth(100);
        textPassword.setMaxWidth(100);
        textPin.setMaxWidth(100);
    }
    
    private void createButtons() {
        sign = new Button("Sign In");
        makeAccount = new Button("Make an Account");
        
       makeAccount.setOnAction(event -> {
            this.handleStage(primaryStage, 1);
        });
       
       sign.setOnAction(event -> {
           //Enter code for signing in here
       });
    }
    
    private void createVBox() {
        vbox = new VBox(10, user, textUser, password, textPassword, pin, textPin, sign, makeAccount);
        vbox.setAlignment(Pos.CENTER);
    }
}
