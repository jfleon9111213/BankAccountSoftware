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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuManager extends BaseHandler{
    private Label welcome;
    private Label savingsLabel;
    private Label savingsMoney;
    private Label checkingsLabel;
    private Label checkingsMoney;
    private Label creditLabel;
    private Label balanceLabel;
    private Label outstandingLabel;
    private Label creditAvailable;
    private Label remainingLabel;
    
    private Button savingsManage;
    private Button checkingsManage;
    private Button creditManage;
    private Button createCredit;
    private Button signOut;
    
    private HBox savingsH;
    private HBox checkingsH;
    private HBox creditH;
    private HBox creditButtonsH;
    private VBox rootNode;
    
    private Stage primaryStage;
    
    public MainMenuManager() {
        super.sceneNumber = 2;
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
        createLabel();
        createButtons();
        createLayout();
        
        Scene scene = new Scene(rootNode, 600, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Banking Application");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private void createLabel() {
        welcome = new Label("Welcome (Name)");
        savingsLabel = new Label("Savings Account:");
        savingsMoney = new Label("$(DOLLARS).(CENTS)");
        savingsMoney.setStyle("-fx-border-style: solid");
        checkingsLabel = new Label("Checkings Account:");
        checkingsMoney = new Label("$(DOLLARS).(CENTS)");
        checkingsMoney.setStyle("-fx-border-style: solid");
        creditLabel = new Label("Credit Card:");
        balanceLabel = new Label("$(DOLLARS).(CENTS)");
        balanceLabel.setStyle("-fx-border-style: solid");
        outstandingLabel = new Label("Outstanding");
        creditAvailable = new Label("$(DOLLARS).(CENTS)");
        creditAvailable.setStyle("-fx-border-style: solid");
        remainingLabel = new Label("Remaining");
    }
    
    private void createButtons() {
        savingsManage = new Button("Manage Savings");
        checkingsManage = new Button("Manage Checkings");
        creditManage = new Button("Manage Credit Card");
        createCredit = new Button("Apply for Credit Card");
        if(false) {//credit card account exists
            createCredit.setDisable(true);
        }
        else {
            creditManage.setDisable(true);
        }
        signOut = new Button("Sign Out");
        
        savingsManage.setOnAction(event -> {
            this.handleStage(primaryStage, 3);
        });
        
        checkingsManage.setOnAction(event -> {
            this.handleStage(primaryStage, 4);
        });
        
        creditManage.setOnAction(event -> {
            this.handleStage(primaryStage, 5);
        });
        
        createCredit.setOnAction(event -> {
            this.handleStage(primaryStage, 6);
        });
        
        signOut.setOnAction(event -> {
            this.handleStage(primaryStage, 0);
        });
    }
    
    private void createLayout() {
        savingsH = new HBox(20, savingsLabel, savingsMoney, savingsManage);
        savingsH.setPadding(new Insets(0, 0, 0, 50));
        checkingsH = new HBox(20, checkingsLabel, checkingsMoney, checkingsManage);
        checkingsH.setPadding(new Insets(0, 0, 0, 50));
        creditH = new HBox(20, creditLabel, balanceLabel, outstandingLabel, creditAvailable, remainingLabel);
        creditH.setPadding(new Insets(0, 0, 0, 50));
        creditButtonsH = new HBox(30, creditManage, createCredit);
        creditButtonsH.setAlignment(Pos.CENTER);
        rootNode = new VBox(20, welcome, savingsH, checkingsH, creditH, creditButtonsH, signOut);
        rootNode.setAlignment(Pos.CENTER);
    }
    
}
