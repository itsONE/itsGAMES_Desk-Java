package utfpr.itsone.view.login;

import utfpr.itsone.model.dao.UserData;
import utfpr.itsone.model.User;
import utfpr.itsone.view.menu.TopBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class SignUp extends SignIn{
    private JLabel emailLabel = new JLabel("Email");
    private JTextField emailField = new JTextField();
    protected JLabel passwordLabel2 = new JLabel("Confirm Password");
    protected JPasswordField passwordField2 = new JPasswordField();

    public SignUp(JFrame parent, TopBar topBar) {
        super(parent, topBar);
        remove(9);
        remove(8);
        register();
    }

    public void register() {
        passwordField2.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, new Color(0xcccccc)));
        passwordField2.setMaximumSize(new Dimension(300,userField.getPreferredSize().height+5));
        passwordField2.setBackground(new Color(0x000a1f));
        passwordField2.setForeground(new Color(0xcccccc));
        passwordField2.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel2.setForeground(new Color(0x333436));


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
        passwordField2.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent arg0) {
                passwordLabel2.setForeground(new Color(0x285888));
            }

            public void focusLost(FocusEvent arg0) {
                passwordLabel2.setForeground(new Color(0x333436));
            }
        });
        remove(0);
        add(Box.createRigidArea(new Dimension(0, 100)),0);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(passwordLabel2);
        add(Box.createRigidArea(new Dimension(0, 2)));
        add(passwordField2);
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

    public String valData(String user,String password, String password2, String email){
        String error = "";
        if (user.isEmpty())
            error += "Nome de usuário é obrigatório\n";
        if (password.isEmpty())
            error += "Senha de usuário é obrigatório\n";
        if (email.isEmpty())
            error += "Email de usuário é obrigatório\n";
        if(validaUserName(user))
            error += "Nome de usuário inválido\n";
        if(validaEmail(email))
            error += "Email de usuário inválido\n";
        if(!password.equals(password2))
            error += "Senhas não são iguais\n";
        if(password.length()<5)
            error += "Senha deve ter 5 ou mais caracteres\n";
        return error;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String error = valData(userField.getText(),passwordField.getText(),passwordField2.getText(),emailField.getText());
        if(error.isEmpty()) {
            /*UserData.getData().addUser(new User(userField.getText(), emailField.getText(), encrpPass(passwordField.getText())));
            User user = UserData.getData().getUsers().get(1);*/
            parent.dispose();
        }
        else
            showMessageDialog(null, error);
    }
}
