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
        setSize(420, 320);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 245, 255));

        JLabel titleLabel = new JLabel("Flappy Bird Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setBounds(85, 30, 260, 35);
        add(titleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(60, 100, 100, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(155, 100, 190, 28);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 145, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(155, 145, 190, 28);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(90, 210, 110, 35);
        loginButton.addActionListener(this);
        add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(220, 210, 110, 35);
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
