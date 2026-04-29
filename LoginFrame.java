import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {
    JLabel titleLabel, userLabel, passLabel;
    JTextField userField;
    JPasswordField passField;
    JButton loginButton, registerButton;
    UserManager userManager = new UserManager();

    LoginFrame() {
        setTitle("Flappy Bird Login");
        setSize(350, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(140, 20, 100, 30);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(40, 70, 80, 25);

        userField = new JTextField();
        userField.setBounds(130, 70, 150, 25);

        passLabel = new JLabel("Password:");
        passLabel.setBounds(40, 110, 80, 25);

        passField = new JPasswordField();
        passField.setBounds(130, 110, 150, 25);

        loginButton = new JButton("Login");
        loginButton.setBounds(60, 160, 90, 30);
        loginButton.addActionListener(this);

        registerButton = new JButton("Register");
        registerButton.setBounds(180, 160, 100, 30);
        registerButton.addActionListener(this);

        add(titleLabel);
        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(loginButton);
        add(registerButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = userField.getText();
        String password = String.valueOf(passField.getPassword());

        if (e.getSource() == loginButton) {
            if (userManager.loginUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                openGame();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }

        if (e.getSource() == registerButton) {
            new RegisterFrame();
        }
    }

    public void openGame() {
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBird flappyBird = new FlappyBird();

        frame.add(flappyBird);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        flappyBird.requestFocus();
    }
}