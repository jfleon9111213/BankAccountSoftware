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
import javafx.stage.Stage;

public abstract class BaseHandler {
    protected BaseHandler successor;
    protected BaseHandler predecessor;
    protected int sceneNumber;
    protected Stage primaryStage;
    
    public abstract void handleStage(Stage primaryStage, int sceneNumber);

    public void setSuccessor(BaseHandler successor) {
        this.successor = successor;
    }
  
    public void setPredecessor(BaseHandler predecessor) {
        this.predecessor = predecessor;
    }
  
    public void setSceneNumber(int sceneNumber) {
        this.sceneNumber = sceneNumber;
    }
}
