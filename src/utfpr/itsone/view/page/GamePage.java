package utfpr.itsone.view.page;

import utfpr.itsone.controller.GameController;
import utfpr.itsone.controller.Session;
import utfpr.itsone.controller.core.ScriptPython;
import utfpr.itsone.model.Game;
import utfpr.itsone.model.dao.GameData;
import utfpr.itsone.view.body.GameList;
import utfpr.itsone.view.body.GameView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


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
    private JTextPane info;
    private JTextPane text;
    private JLabel cover;
    private Font customFont;
    private boolean active;
    private JButton addGame;
    private JComboBox reviewUser = new JComboBox<Integer>();
    private JLabel yourReview;
    private JLabel avgReview;
    private GameList gameList;
    private ScriptPython sp;
    private GameData db = new GameData();

    public GamePage(Game game, GameController controller) throws HeadlessException {
        super(game.getName());
        this.gameList = new GameList(controller,5);
        this.game = game;
        this.controller = controller;
        this.sp = controller.getScriptPython();
        this.active = controller.getGameUser(game);
        this.panel = new JPanel(new BorderLayout());
        this.panelControl = new JPanel();
        this.panelInfo = new JPanel(new BorderLayout());
        this.panelContent = new JPanel(new BorderLayout());
        this.title = new JLabel(game.getName());
        this.cover = new JLabel();
        this.text = new JTextPane();
        this.info = new JTextPane();
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

    public void components() {
        add(panel, BorderLayout.CENTER);
        panel.add(panelControl, BorderLayout.WEST);
        panel.add(panelInfo, BorderLayout.CENTER);
        panelInfo.add(panelContent, BorderLayout.CENTER);
        panelControl.setLayout(new BoxLayout(panelControl, BoxLayout.Y_AXIS));
        panelInfo.add(title, BorderLayout.NORTH);
        panelControl.add(cover);
        panelContent.add(text, BorderLayout.CENTER);
        panelContent.add(info, BorderLayout.NORTH);
        panelContent.add(gameList, BorderLayout.SOUTH);
        panelControl.setPreferredSize(new Dimension(250, 1000));
        configComponents();
        configRec();
    }

    public void configRec(){
        gameList.removeAll();
        this.gameList.setSizeVal(2);
        this.gameList.setBackground(new Color(0x000717));
        try {
            ArrayList<Integer> game_id = sp.runScript(game.getId());
            for(Integer id : game_id){
                this.gameList.createGameView(db.getGame(id));
            }
        } catch (IOException e) {
            e.printStackTrace();
            for(int i = 0; i < 4; i++)
                this.gameList.createGameView(game);
        } catch (InterruptedException e) {
            e.printStackTrace();
            for(int i = 0; i < 4; i++)
                this.gameList.createGameView(game);
        }
    }

    public void configComponents() {
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

        Dimension dimension = new Dimension(190, 270);
        ImageIcon img = new ImageIcon(game.getCover());
        Image img2 = img.getImage();
        Image newimg = img2.getScaledInstance(img.getIconWidth(), dimension.height, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        Border line = new LineBorder(new Color(0x001436), 2, true);
        cover.setBorder(line);
        cover.setIcon(newIcon);
        cover.setPreferredSize(dimension);
        cover.revalidate();
        cover.setOpaque(false);
        cover.repaint();
        buttons();

        info.setText("Lançamento: " + game.getDate().toString() + "\n"
                + "Desenvolvedora: " + game.getDeveloper() + "\n"
                + "Site: " + game.getSite() + "\n"
                + "Classificação: " + game.getRating() + "\n");
        info.setPreferredSize(new Dimension(800, 100));
        info.setForeground(Color.WHITE);
        info.setFont(customFont.deriveFont(Font.TRUETYPE_FONT, 15));
        info.setEditable(false);
        info.setOpaque(false);

        text.setText(game.getDescription());
        text.setPreferredSize(new Dimension(800, 500));
        text.setForeground(Color.WHITE);
        text.setFont(customFont.deriveFont(Font.TRUETYPE_FONT, 15));
        text.setEditable(false);
        text.setOpaque(false);

        panelControl.add(reviewUser);
        for (int i = 1; i <= 10; i++) {
            reviewUser.addItem(new Integer(i));
        }
        reviewUser.setMaximumSize(new Dimension(305, 50));

        panelControl.add(yourReview);
        panelControl.add(avgReview);

    }

    private void buttons() {
        addGame = new JButton();
        JButton addReview = new JButton();
        this.yourReview = new JLabel();
        this.avgReview = new JLabel();
        Color colorForeground = new Color(0x8D8D8D);
        Color colorBackground = new Color(0xF1F1F1);
        addGame.setForeground(colorForeground);
        addGame.setBackground(colorBackground);
        addReview.setText("SALVAR NOTA");
        addReview.setForeground(colorForeground);
        addReview.setBackground(colorBackground);
        Border line = new LineBorder(colorForeground);
        Border margin = new EmptyBorder(10, 45, 10, 45);
        Border margin2 = new EmptyBorder(10, 35, 10, 35);
        Border compound = new CompoundBorder(line, margin);
        Border compound2 = new CompoundBorder(line, margin2);
        addGame.setBorder(compound);
        addReview.setBorder(compound2);
        panelControl.add(addGame);
        panelControl.add(addReview);
        setConfig();
        addGame.addActionListener(e -> {
            if (Session.getSession().getId() > 0) {
                controller.addGameUser(game, active);
                this.active = controller.getGameUser(game);
                setConfig();
            }
        });
        addReview.addActionListener(e -> {
            if (Session.getSession().getId() > 0) {
                if (!active)
                    controller.addGameUser(game, false);
                this.active = controller.getGameUser(game);
                controller.addReviewGame(game, reviewUser.getSelectedIndex() + 1);
                setConfig();
            }
        });
        yourReview.setForeground(Color.WHITE);
        yourReview.setFont(customFont.deriveFont(Font.BOLD, 15));
        avgReview.setForeground(Color.WHITE);
        avgReview.setFont(customFont.deriveFont(Font.BOLD, 15));
        yourReview.setBorder(new EmptyBorder(15,5,5,5));
        avgReview.setBorder(new EmptyBorder(15,5,5,5));
    }

    public void setConfig() {
        if (active) {
            addGame.setText("REMOVER");
            int j = controller.yourReview(game);
            if (j > 0)
                yourReview.setText("Sua nota: " + j);
        } else {
            addGame.setText("ADICIONAR");
            yourReview.setText("");
        }
        float i = controller.avgReview(game);
        if (i > 0)
            avgReview.setText("Média Geral: " + i);
        else
            avgReview.setText("Média Geral: n/a");
    }

}
