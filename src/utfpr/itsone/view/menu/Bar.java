package utfpr.itsone.view.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bar extends JPanel {
    JLabel menu = new JLabel();
    JLabel plus = new JLabel();
    JLabel favorite = new JLabel();
    public Bar() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        config();
        initComponents();
    }

    private void config() {
        setBackground(new Color(0x000a1f));
    }

    public void initComponents(){
        setIcons();
        menu.setBorder(new EmptyBorder(55,12,0,12));
        plus.setBorder(new EmptyBorder(50,9,0,0));
        favorite.setBorder(new EmptyBorder(50,9,0,0));
        add(menu);
        add(plus);
        add(favorite);
    }

    public void setIcons(){
        menu.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/menu-25.png")));
        plus.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/plus-math-30.png")));
        favorite.setIcon(new ImageIcon(setImage("utfpr/itsone/resources/icons/filled-bookmark-ribbon-30.png")));
    }

    public BufferedImage setImage(String path){
        try {
            return ImageIO.read(getClass().getClassLoader().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
