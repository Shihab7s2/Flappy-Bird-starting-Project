import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel title, userLbl, passLbl;
    JTextField userTxt;
    JPasswordField passTxt;
    JButton btnLogin, btnRegister;

    UserManager um = new UserManager();

    LoginFrame() {

        setTitle("Flappy Bird Login");
        setSize(360, 260);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(140, 20, 100, 30);

        userLbl = new JLabel("Username:");
        userLbl.setBounds(40, 70, 100, 25);

        userTxt = new JTextField();
        userTxt.setBounds(130, 70, 160, 25);

        passLbl = new JLabel("Password:");
        passLbl.setBounds(40, 110, 100, 25);

        passTxt = new JPasswordField();
        passTxt.setBounds(130, 110, 160, 25);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(60, 170, 90, 30);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(180, 170, 100, 30);

        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);

        add(title);
        add(userLbl);
        add(userTxt);
        add(passLbl);
        add(passTxt);
        add(btnLogin);
        add(btnRegister);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String user = userTxt.getText();
        String pass = new String(passTxt.getPassword());

        if (e.getSource() == btnLogin) {

            if (um.loginUser(user, pass)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                dispose();
                openGame();
            } else {
                JOptionPane.showMessageDialog(this, "Wrong username or password");
            }
        }

        if (e.getSource() == btnRegister) {
            new RegisterFrame();
        }
    }

    void openGame() {

        JFrame f = new JFrame("Flappy Bird");
        FlappyBird game = new FlappyBird();

        f.add(game);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        game.requestFocus();
    }
}