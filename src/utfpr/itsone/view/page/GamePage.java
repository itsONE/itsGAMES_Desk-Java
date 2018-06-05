package utfpr.itsone.view.page;

import utfpr.itsone.controller.GameController;
import utfpr.itsone.model.Game;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;


public class GamePage extends ImplementPage {
    private GameController controller;
    public static final int WIDTH = 142;
    public static final int HEIGHT = WIDTH / 32 * 22;
    public static final int SCALE = 7;
    public static final Dimension DIMENSIONS = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
    private final Game game;
    private final JPanel panelControl;
    private final JPanel panelInfo;
    private final JPanel panelContent;
    private final JPanel panel;
    private final JLabel title;
    private JTextPane text;
    private JLabel date;
    private JLabel website;
    private JLabel rating;
    private JLabel developer;
    private JLabel cover;
    private Font customFont;


    public GamePage(Game game, GameController controller) throws HeadlessException {
        super(game.getName());
        this.game = game;
        this.controller = controller;
        this.panel = new JPanel(new BorderLayout());
        this.panelControl = new JPanel();
        this.panelInfo = new JPanel(new BorderLayout());
        this.panelContent = new JPanel();
        this.title = new JLabel(game.getName());
        this.cover = new JLabel();
        this.text = new JTextPane();
        setUndecorated(true);
        setPreferredSize(DIMENSIONS);
        revalidate();
        setLayout(new BorderLayout());
        init();
        components();
        pack();
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void components(){
        add(panel,BorderLayout.CENTER);
        panel.add(panelControl,BorderLayout.WEST);
        panel.add(panelInfo, BorderLayout.CENTER);
        panelInfo.add(panelContent,BorderLayout.CENTER);
        panelControl.setLayout(new BoxLayout(panelControl,BoxLayout.Y_AXIS));
        panelInfo.add(title,BorderLayout.NORTH);
        panelControl.add(cover);
        panelContent.add(text,BorderLayout.CENTER);
        configComponents();
    }

    public void configComponents(){
        panel.setBackground(new Color(0x000715));
        panelControl.setOpaque(false);
        panelInfo.setOpaque(false);
        panelContent.setOpaque(false);

        InputStream is = getClass().getResourceAsStream("/utfpr/itsone/resources/fonts/HelveticaNeueLTStd-Th.otf");
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        title.setForeground(Color.WHITE);
        title.setFont(customFont.deriveFont(Font.BOLD, 45));

        Dimension dimension = new Dimension(190,270);
        ImageIcon img = new ImageIcon(game.getCover());
        Image img2 = img.getImage();
        Image newimg = img2.getScaledInstance(img.getIconWidth(),dimension.height, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        Border line = new LineBorder(new Color(0x001436),2,true);
        cover.setBorder(line);
        cover.setIcon(newIcon);
        cover.setPreferredSize(dimension);
        cover.revalidate();
        cover.setOpaque(false);
        cover.repaint();
        buttons();

        text.setText(game.getDescription());
        text.setPreferredSize(new Dimension(800,500));
        text.setForeground(Color.WHITE);
        text.setFont(customFont.deriveFont(Font.TRUETYPE_FONT, 15));
        text.setEditable(false);
        text.setOpaque(false);
    }

    private void buttons() {
        JButton addGame = new JButton();
        JButton addReview = new JButton();
        Color colorForeground = new Color(0x8D8D8D);
        Color colorBackground = new Color(0xF1F1F1);
        addGame.setText("ADICIONAR");
        addGame.setForeground(colorForeground);
        addGame.setBackground(colorBackground);
        addReview.setText("NOTA");
        addReview.setForeground(colorForeground);
        addReview.setBackground(colorBackground);
        Border line = new LineBorder(colorForeground);
        Border margin = new EmptyBorder(10, 20, 10, 20);
        Border margin2 = new EmptyBorder(10, 35, 10, 35);
        Border compound = new CompoundBorder(line, margin);
        Border compound2 = new CompoundBorder(line, margin2);
        addGame.setBorder(compound);
        addReview.setBorder(compound2);
        panelControl.add(addGame);
        panelControl.add(addReview);
    }

}
