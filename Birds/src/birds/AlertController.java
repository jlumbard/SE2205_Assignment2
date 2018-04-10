/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birds;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Kevin
 */



public class AlertController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
 @FXML
public Label msg; 
 
 public void setMessage (String txt){
     msg.setText(txt);
 }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
