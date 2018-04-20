package utfpr.itsone.view.body;

import utfpr.itsone.model.core.Game;
import utfpr.itsone.view.Index;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Header extends JPanel {
    Font customFont;
    JLabel title = new JLabel("itsGAMES");
    JPanel body = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel description = new JLabel("",SwingConstants.CENTER);
    Game game;

    public Header() {
        InputStream is = getClass().getResourceAsStream("/utfpr/itsone/resources/fonts/HelveticaNeueLTStd-Th.otf");
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //title.setFont(new Font("HelveticaNeueLT Std Thin", Font.PLAIN, 55));
        title.setFont(customFont.deriveFont(Font.BOLD, 55));
        title.setBorder(new EmptyBorder(100,2000,100,2000));
        header.setOpaque(false);
        header.add(title);
        add(header,BorderLayout.NORTH);
        /*setLayout(new FlowLayout(FlowLayout.CENTER));
        //setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        title.setFont(new Font("HelveticaNeueLT Std Thin", Font.PLAIN, 55));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(new EmptyBorder(100,2000,100,2000));
        //title.setBorder(new EmptyBorder(100,0,100,0));
        add(title);*/
    }

    public JLabel getTitle() {
        return title;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setSizeFont(int value){
        title.setFont(customFont.deriveFont(Font.BOLD, value/18));
        if(value>700)
            description.setFont(customFont.deriveFont(Font.PLAIN, (float)value/1000*16));
    }

    public void setDescription(String text) {
        description.setText("<html><p>"+text+"</p></html>");
    }

    public void create(){
        //description.setFont(new Font("HelveticaNeueLT Std Thin", Font.PLAIN, 16));
        description.setFont(customFont.deriveFont(Font.PLAIN, 16));
        description.setForeground(new Color(0xffffff));
        description.setBorder(new EmptyBorder(0,2000,10,2000));
        title.setBorder(new EmptyBorder(50,2000,50,2000));
        body.setOpaque(false);
        body.add(description);
        add(body,BorderLayout.CENTER);
        revalidate();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!Objects.isNull(game)) {
            Image imagem = new ImageIcon(game.getBackground()).getImage();
            g.drawImage(imagem, 0, 0, this);
        } else {
            g.drawImage(null, 0, 0, this);
        }
    }

}
