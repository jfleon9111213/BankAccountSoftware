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
        
        logIn.setSuccessor(createAccount);
        createAccount.setPredecessor(logIn);
        
        logIn.handleStage(primaryStage, 0);
    }
    
}
