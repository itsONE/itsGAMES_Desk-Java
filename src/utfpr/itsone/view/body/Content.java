package utfpr.itsone.view.body;

import utfpr.itsone.data.GameData;
import utfpr.itsone.model.core.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Content extends JPanel {
    private ArrayList<GameView> gameViews = new ArrayList<>();

    public Content(JFrame parent){
        super();
        //setLayout(new GridLayout(5,10));
        setLayout(new FlowLayout());
        JScrollPane scrollBar = new JScrollPane(this);
        //this.setPreferredSize(new Dimension(100,100));
        //parent.add(scrollBar);
        setBackground(new Color(0x000715));
        for (Game game : GameData.getData().getGames()){
            GameView gameView = new GameView(game);
            gameViews.add(gameView);
            add(gameView);
            ImageIcon img = new ImageIcon(game.getCover());
            Image img2 = img.getImage();
            Image newimg = img2.getScaledInstance(100, 125, java.awt.Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newimg);
            gameView.setIcon(newIcon);
            gameView.setPreferredSize(new Dimension(100, 125));
            gameView.revalidate();
            gameView.setOpaque(false);
            gameView.repaint();
        }

    }
}
