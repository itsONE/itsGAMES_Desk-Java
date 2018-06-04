package utfpr.itsone.view.page;

import utfpr.itsone.controller.GameController;
import utfpr.itsone.model.Game;

import javax.swing.*;
import java.awt.*;


public class GamePage extends ImplementPage {
    private GameController controller;
    public static final int WIDTH = 142;
    public static final int HEIGHT = WIDTH / 32 * 22;
    public static final int SCALE = 7;
    public static final Dimension DIMENSIONS = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);

    public GamePage(Game game, GameController controller) throws HeadlessException {
        super(game.getName());
        this.controller = controller;
        setUndecorated(true);
        setPreferredSize(DIMENSIONS);
        revalidate();
        setLayout(new BorderLayout());
        init();
        pack();
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
