package utfpr.itsone.view.body;

import utfpr.itsone.controller.GameController;
import utfpr.itsone.model.Game;
import utfpr.itsone.view.Index;
import utfpr.itsone.view.page.GamePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameList extends JPanel implements MouseListener {
    private GameController controller;
    private static final int PADDING = 10;
    private int size = 3;
    private Dimension dimension;

    public GameList(GameController controller) {
        this.controller = controller;
        setLayout(new FlowLayout(FlowLayout.CENTER, PADDING, PADDING));
        setPreferredSize(new Dimension(900,1000));
        setOpaque(false);
    }

    public GameList(GameController controller, int v) {
        this.controller = controller;
    }

    public void createGameView(Game game){
        GameView gameView = new GameView(game);
        this.dimension = sizeGameView();
        add(gameView);
        ImageIcon img = new ImageIcon(game.getCover());
        Image img2 = img.getImage();
        Image newimg = img2.getScaledInstance(img.getIconWidth(),dimension.height, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        gameView.setIcon(newIcon);
        gameView.setPreferredSize(dimension);
        gameView.revalidate();
        gameView.setOpaque(false);
        gameView.repaint();
        gameView.addMouseListener(this);
    }

    public Dimension sizeGameView(){
        switch (size){
            case 1: return new Dimension(120,171);
            case 2: return new Dimension(141,200);
            case 3: return new Dimension(190,270);
            case 4: return new Dimension(211,300);
        }
        return null;
    }

    public int getSizeVal() {
        return size;
    }

    public void setSizeVal(int size) {
        if(size > 0 && size < 5)
            this.size = size;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (arg0.getSource() instanceof GameView) {
            Game game = ((GameView) arg0.getSource()).getGame();
            new GamePage(game,controller);
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        if (arg0.getSource() instanceof GameView) {
                Game game = ((GameView) arg0.getSource()).getGame();
                controller.setHeader(game);
        }
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        if (arg0.getSource() instanceof GameView)
            controller.setStandardHeader();
    }

    public void setDimension(Dimension dimension) {
        setPreferredSize(dimension);
    }
}
