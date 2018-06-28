package utfpr.itsone.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SidebarController implements Initializable {

    @FXML
    private VBox sidebar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void home(MouseEvent event) {
        BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        Parent contentarea = null;
        try {
            contentarea = FXMLLoader.load(getClass().getResource("/utfpr/itsone/view/ContentArea.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        border_pane.setCenter(contentarea);
    }
    
}
