package utfpr.itsone.view.login;

import utfpr.itsone.controller.Session;
import utfpr.itsone.data.UserData;
import utfpr.itsone.model.core.User;
import utfpr.itsone.view.menu.TopBar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class SignIn extends JPanel implements ActionListener {
    protected JFrame parent;
    protected TopBar topBar;
    protected JLabel userLabel = new JLabel("Username");
    protected JTextField userField = new JTextField();
    protected JLabel passwordLabel = new JLabel("Password");
    protected JPasswordField passwordField = new JPasswordField();

    public SignIn(JFrame parent, TopBar topBar){
        this.parent = parent;
        this.topBar = topBar;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(new Color(0x000a1f));
        createFields();
        JButton submit = styleButtons();
        submit.setText("SIGN IN");
        add(Box.createRigidArea(new Dimension(0, 25)));
        add(submit);
        /*submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("login");
            }
        });*/
        submit.addActionListener(this);
    }

    private void createFields() {
        userField.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, new Color(0xcccccc)));
        userField.setMaximumSize(new Dimension(300,userField.getPreferredSize().height+5));
        userField.setBackground(new Color(0x000a1f));
        userField.setForeground(new Color(0xcccccc));
        passwordField.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, new Color(0xcccccc)));
        passwordField.setMaximumSize(new Dimension(300,userField.getPreferredSize().height+5));
        passwordField.setBackground(new Color(0x000a1f));
        passwordField.setForeground(new Color(0xcccccc));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userLabel.setForeground(new Color(0x333436));
        passwordLabel.setForeground(new Color(0x333436));
        add(Box.createRigidArea(new Dimension(0, 150)));
        add(userLabel);
        add(Box.createRigidArea(new Dimension(0, 2)));
        add(userField);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(passwordLabel);
        add(Box.createRigidArea(new Dimension(0, 2)));
        add(passwordField);
    }

    public JButton styleButtons(){
        JButton submit = new JButton();
        Color colorForeground = new Color(0x8D8D8D);
        Color colorBackground = new Color(0xF1F1F1);
        submit.setForeground(colorForeground);
        submit.setBackground(colorBackground);
        Border line = new LineBorder(colorForeground);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        submit.setBorder(compound);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        return submit;
    }

    public boolean validaUserName(String user){
        for(User userName : UserData.getData().getUsers()){
            if(userName.getUsername().equals(user)){
                return true;
            }
        }
        return false;
    }

    public boolean validaEmail(String email){
        for(User userName : UserData.getData().getUsers()){
            if(userName.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public boolean validaSenha(String senha){
        for(User userName : UserData.getData().getUsers()){
            if(userName.getPassword().equals(senha)){
                return true;
            }
        }
        return false;
    }

    public long acess(){
        for(User userName : UserData.getData().getUsers()){
            if(userField.getText().equals(userName.getUsername()) && passwordField.getText().equals(userName.getPassword())){
                return userName.getId();
            }
        }
        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (validaUserName(userField.getText()) && validaSenha(passwordField.getText())){
            Session.getSession().setId(acess());
            topBar.addUser();
            parent.dispose();
        } else {
            showMessageDialog(null, "Usuário e/ou senha inválidos");
        }
    }
}
