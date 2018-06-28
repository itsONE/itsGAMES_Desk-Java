package utfpr.itsone.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import utfpr.itsone.model.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class HeaderController  implements Initializable {
    public Label title;
    public VBox bg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public Label getTitle() {
        return title;
    }

    public void setBackground(String background) {
        String image = getClass().getClassLoader().getResource("utfpr/itsone/resources/background/"+background).toExternalForm();
        bg.setStyle("-fx-background-image: url('" + image + "'); -fx-background-size: cover;");
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-0.7);
        bg.setEffect(colorAdjust);
    }

    public void setTitle(String title) {
        this.title.setText(title);
        if(title.equals("ITSONE"))
            this.title.setTextFill(Color.web("#000000"));
        else
            this.title.setTextFill(Color.web("#ffffff"));

    }
}
