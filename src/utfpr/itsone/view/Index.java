package utfpr.itsone.view;

import utfpr.itsone.controller.CreateObj;
import utfpr.itsone.data.GameData;
import utfpr.itsone.model.core.Game;
import utfpr.itsone.view.body.Content;
import utfpr.itsone.view.body.Header;
import utfpr.itsone.view.menu.Bar;
import utfpr.itsone.view.menu.ComponentResizer;
import utfpr.itsone.view.menu.TopBar;

import javax.swing.*;
import java.awt.*;

public class Index extends JFrame {
    public static final int WIDTH = 142;
    public static final int HEIGHT = WIDTH / 32 * 22;
    public static final int SCALE = 7;
    public static final String NAME = "itsGAMES";
    public static final Dimension DIMENSIONS = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
    private Bar bar = new Bar();
    private TopBar topBar;

    public Index() throws HeadlessException {
        super(NAME);
        new CreateObj();
        Game game = GameData.getData().getGames().get(0);
        setUndecorated(true);
        setPreferredSize(DIMENSIONS);
        setMinimumSize(new Dimension(WIDTH*3,HEIGHT*5));
        ComponentResizer cr = new ComponentResizer();
        cr.registerComponent(this);
        cr.addAction(this);
        revalidate();
        setLayout(new BorderLayout());
        pack();
        topBar = new TopBar(this);
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init() {
        add(bar, BorderLayout.WEST);
        JPanel nav = new JPanel(new BorderLayout());
        add(nav, BorderLayout.CENTER);
        nav.add(topBar, BorderLayout.NORTH);
        JPanel body = new JPanel(new BorderLayout());
        nav.add(body,BorderLayout.CENTER);
        body.add(new Header(),BorderLayout.NORTH);
        body.add(new Content(this), BorderLayout.CENTER);
    }

    public void update(){
        topBar.getSeperatorCenter().setMaximumSize(new Dimension(getWidth()-620, 0));
    }
}
