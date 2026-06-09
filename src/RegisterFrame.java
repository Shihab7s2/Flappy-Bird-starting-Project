import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame implements ActionListener {
    JTextField fullNameField;
    JTextField emailField;
    JTextField phoneNumberField;
    JTextField usernameField;
    JPasswordField passwordField;
    JPasswordField confirmPasswordField;
    JButton registerButton;
    JButton backButton;
    UserManager userManager;

    public RegisterFrame() {
        userManager = new UserManager();

        setTitle("Flappy Bird Registration");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 245, 255));

        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setBounds(120, 25, 230, 35);
        add(titleLabel);

        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setBounds(65, 85, 110, 25);
        add(fullNameLabel);

        fullNameField = new JTextField();
        fullNameField.setBounds(185, 85, 180, 28);
        add(fullNameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(65, 125, 110, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(185, 125, 180, 28);
        add(emailField);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(65, 165, 110, 25);
        add(phoneNumberLabel);

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(185, 165, 180, 28);
        add(phoneNumberField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(65, 205, 110, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(185, 205, 180, 28);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(65, 245, 110, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(185, 245, 180, 28);
        add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(65, 285, 120, 25);
        add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(185, 285, 180, 28);
        add(confirmPasswordField);

        JLabel noteLabel = new JLabel("Password must be at least 6 characters");
        noteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        noteLabel.setBounds(185, 315, 240, 20);
        add(noteLabel);

        registerButton = new JButton("Register");
        registerButton.setBounds(95, 395, 115, 35);
        registerButton.addActionListener(this);
        add(registerButton);

        backButton = new JButton("Back");
        backButton.setBounds(235, 395, 115, 35);
        backButton.addActionListener(this);
        add(backButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String fullName = fullNameField.getText().trim();
            String email = emailField.getText().trim();
            String phoneNumber = phoneNumberField.getText().trim();
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (fullName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required");
            } else if (fullName.contains(",") || email.contains(",") || phoneNumber.contains(",") || username.contains(",") || password.contains(",") || confirmPassword.contains(",")) {
                JOptionPane.showMessageDialog(this, "Comma is not allowed");
            } else if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(this, "Enter a valid email address");
            } else if (!isValidPhoneNumber(phoneNumber)) {
                JOptionPane.showMessageDialog(this, "Phone number must be 11 digits");
            } else if (password.length() < 6) {
                JOptionPane.showMessageDialog(this, "Password must be at least 6 characters");
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Confirm password does not match");
            } else {
                User user = new User(fullName, email, phoneNumber, username, password);
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

    public boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".") && email.indexOf("@") > 0 && email.lastIndexOf(".") > email.indexOf("@");
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11) {
            return false;
        }

        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
