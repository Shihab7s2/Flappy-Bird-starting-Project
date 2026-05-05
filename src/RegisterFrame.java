import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame implements ActionListener {
    JTextField nameField;
    JTextField emailField;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton registerButton;
    JButton backButton;
    UserManager userManager;

    public RegisterFrame() {
        userManager = new UserManager();

        setTitle("Flappy Bird Registration");
        setSize(430, 380);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(115, 25, 220, 35);
        add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(60, 85, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(160, 85, 190, 25);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(60, 125, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(160, 125, 190, 25);
        add(emailField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(60, 165, 100, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(160, 165, 190, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 205, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 205, 190, 25);
        add(passwordField);

        JLabel noteLabel = new JLabel("Password must be at least 6 characters");
        noteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        noteLabel.setBounds(160, 232, 230, 20);
        add(noteLabel);

        registerButton = new JButton("Register");
        registerButton.setBounds(85, 280, 110, 32);
        registerButton.addActionListener(this);
        add(registerButton);

        backButton = new JButton("Back");
        backButton.setBounds(220, 280, 110, 32);
        backButton.addActionListener(this);
        add(backButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required");
            } else if (name.contains(",") || email.contains(",") || username.contains(",") || password.contains(",")) {
                JOptionPane.showMessageDialog(this, "Comma is not allowed");
            } else if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(this, "Enter a valid email");
            } else if (password.length() < 6) {
                JOptionPane.showMessageDialog(this, "Password must be at least 6 characters");
            } else {
                User user = new User(name, email, username, password);
                boolean success = userManager.registerUser(user);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Registration successful");
                    dispose();
                    new LoginFrame();
                } else {
                    JOptionPane.showMessageDialog(this, "Username already exists");
                }
            }
        }

        if (e.getSource() == backButton) {
            dispose();
            new LoginFrame();
        }
    }
}
