package utfpr.itsone.controller;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import utfpr.itsone.model.Game;

public class GameView extends StackPane {
    private Game game;

    public GameView(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
