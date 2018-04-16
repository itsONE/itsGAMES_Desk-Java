package utfpr.itsone.view;

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
        JPanel content = new JPanel(new BorderLayout());
        add(content, BorderLayout.CENTER);
        content.add(topBar, BorderLayout.NORTH);
    }

    public void update(){
        topBar.getSeperatorCenter().setMaximumSize(new Dimension(getWidth()-620, 0));
    }
}
