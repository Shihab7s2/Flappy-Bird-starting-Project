import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame implements ActionListener {
    JLabel titleLabel, contactLabel, userLabel, passLabel;
    JTextField contactField, userField;
    JPasswordField passField;
    JButton createButton;
    UserManager userManager = new UserManager();

    RegisterFrame() {
        setTitle("Register");
        setSize(380, 300);
        setLayout(null);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(120, 20, 150, 30);

        contactLabel = new JLabel("Email/Phone:");
        contactLabel.setBounds(40, 70, 100, 25);

        contactField = new JTextField();
        contactField.setBounds(150, 70, 170, 25);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(40, 110, 100, 25);

        userField = new JTextField();
        userField.setBounds(150, 110, 170, 25);

        passLabel = new JLabel("Password:");
        passLabel.setBounds(40, 150, 100, 25);

        passField = new JPasswordField();
        passField.setBounds(150, 150, 170, 25);

        createButton = new JButton("Create Account");
        createButton.setBounds(115, 210, 140, 30);
        createButton.addActionListener(this);

        add(titleLabel);
        add(contactLabel);
        add(contactField);
        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(createButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String contact = contactField.getText();
        String username = userField.getText();
        String password = String.valueOf(passField.getPassword());

        if (contact.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        if (userManager.userExists(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists.");
            return;
        }

        userManager.registerUser(contact, username, password);
        JOptionPane.showMessageDialog(this, "Registration Successful!");
        dispose();
    }
}