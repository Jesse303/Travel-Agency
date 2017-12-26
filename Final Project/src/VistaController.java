/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author User
 */
public class VistaController implements Initializable {

    @FXML
    private Label label, label2;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");

        label.setText("This is a text sample");
        label.setFont(Font.font("Verdana", 20));

        label2.setText("This is a text sample");
        label2.setFont(Font.font("Verdana", 20));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
