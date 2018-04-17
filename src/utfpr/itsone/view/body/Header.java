package utfpr.itsone.view.body;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Header extends JPanel {

    public Header() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel title = new JLabel("itsGAMES");
        title.setFont(new Font("HelveticaNeueLT Std Thin", Font.PLAIN, 55));
        title.setBorder(new EmptyBorder(100,0,100,0));
        add(title);
    }


}
