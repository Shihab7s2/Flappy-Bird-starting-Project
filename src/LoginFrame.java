import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton registerButton;
    UserManager userManager;

    public LoginFrame() {
        userManager = new UserManager();

        setTitle("Flappy Bird Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Flappy Bird Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(85, 25, 250, 35);
        add(titleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(60, 90, 100, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 90, 170, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 130, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 130, 170, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(80, 185, 100, 30);
        loginButton.addActionListener(this);
        add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(200, 185, 100, 30);
        registerButton.addActionListener(this);
        add(registerButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty");
            } else {
                boolean success = userManager.loginUser(username, password);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Login successful");
                    dispose();
                    new MenuFrame(username);
                } else {
                    JOptionPane.showMessageDialog(this, "Wrong username or password");
                }
            }
        }

        if (e.getSource() == registerButton) {
            dispose();
            new RegisterFrame();
        }
    }
}
