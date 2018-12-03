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

import javafx.application.Application;
import javafx.stage.Stage;

public class GUIMain extends Application{
    protected Stage primaryStage;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
        BaseHandler logIn = new LogInHandler();
        BaseHandler createAccount = new AccountCreateHandler();
        BaseHandler mainMenu = new MainMenuManager();
        BaseHandler savingsAccount = new SavingsManager();
        BaseHandler checkingsAccount = new CheckingsHandler();
        BaseHandler creditAccount = new CreditCardHandler();
        BaseHandler createCredit = new CreditCardApplicationHandler();
        
        logIn.setSuccessor(createAccount);
        createAccount.setPredecessor(logIn);
        createAccount.setSuccessor(mainMenu);
        mainMenu.setPredecessor(createAccount);
        mainMenu.setSuccessor(savingsAccount);
        savingsAccount.setPredecessor(mainMenu);
        savingsAccount.setSuccessor(checkingsAccount);
        checkingsAccount.setPredecessor(savingsAccount);
        checkingsAccount.setSuccessor(creditAccount);
        creditAccount.setPredecessor(checkingsAccount);
        creditAccount.setSuccessor(createCredit);
        createCredit.setPredecessor(creditAccount);
        
        logIn.handleStage(primaryStage, 0);
    }
    
}
