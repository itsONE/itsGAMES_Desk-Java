package utfpr.itsone.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import utfpr.itsone.app.Launch;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private BorderPane border_pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeStageDrageable();
        try {
            Parent contentarea = FXMLLoader.load(getClass().getResource("/utfpr/itsone/view/ContentArea.fxml"));
            border_pane.setCenter(contentarea);
            Parent sidebar = FXMLLoader.load(getClass().getResource("/utfpr/itsone/view/Sidebar.fxml"));
            border_pane.setLeft(sidebar);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void makeStageDrageable() {
        border_pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        border_pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Launch.stage.setX(event.getScreenX() - xOffset);
                Launch.stage.setY(event.getScreenY() - yOffset);
                Launch.stage.setOpacity(0.7f);
            }
        });
        border_pane.setOnDragDone((e) -> {
            Launch.stage.setOpacity(1.0f);
        });
        border_pane.setOnMouseReleased((e) -> {
            Launch.stage.setOpacity(1.0f);
        });

    }
}
