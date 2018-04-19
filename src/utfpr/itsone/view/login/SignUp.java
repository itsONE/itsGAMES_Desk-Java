package utfpr.itsone.view.login;

import utfpr.itsone.data.UserData;
import utfpr.itsone.model.core.User;
import utfpr.itsone.view.menu.TopBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class SignUp extends SignIn{
    private JLabel emailLabel = new JLabel("Email");
    private JTextField emailField = new JTextField();

    public SignUp(JFrame parent, TopBar topBar) {
        super(parent, topBar);
        remove(9);
        remove(8);
        register();
    }

    public void register() {
        emailField.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, new Color(0xcccccc)));
        emailField.setMaximumSize(new Dimension(300,emailField.getPreferredSize().height+5));
        emailField.setBackground(new Color(0x000a1f));
        emailField.setForeground(new Color(0xcccccc));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLabel.setForeground(new Color(0x333436));
        emailField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent arg0) {
                emailLabel.setForeground(new Color(0x285888));
            }

            public void focusLost(FocusEvent arg0) {
                emailLabel.setForeground(new Color(0x333436));
            }
        });
        userField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent arg0) {
                userLabel.setForeground(new Color(0x285888));
            }

            public void focusLost(FocusEvent arg0) {
                userLabel.setForeground(new Color(0x333436));
            }
        });
        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent arg0) {
                passwordLabel.setForeground(new Color(0x285888));
            }

            public void focusLost(FocusEvent arg0) {
                passwordLabel.setForeground(new Color(0x333436));
            }
        });
        remove(0);
        add(Box.createRigidArea(new Dimension(0, 100)),0);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(emailLabel);
        add(Box.createRigidArea(new Dimension(0, 2)));
        add(emailField);
        JButton submitRegister = styleButtons();
        /*submitRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Register");
            }
        });*/
        submitRegister.addActionListener(this);
        submitRegister.setText("SIGN UP");
        add(Box.createRigidArea(new Dimension(0, 25)));
        add(submitRegister);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String error = "";
        if (userField.getText().isEmpty())
            error += "Nome de usuário é obrigatório\n";
        if (passwordField.getText().isEmpty())
            error += "Senha de usuário é obrigatório\n";
        if (emailField.getText().isEmpty())
            error += "Email de usuário é obrigatório\n";
        if(validaUserName(userField.getText()))
            error += "Nome de usuário inválido\n";
        if(validaEmail(emailField.getText()))
            error += "Email de usuário inválido\n";

        if(error.isEmpty()) {
            UserData.getData().addUser(new User(userField.getText(), emailField.getText(), passwordField.getText()));
            parent.dispose();
        }
        else
            showMessageDialog(null, error);
    }
}
