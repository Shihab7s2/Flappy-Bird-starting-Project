import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AboutFrame extends JFrame implements ActionListener {
    JButton closeButton;

    public AboutFrame() {
        setTitle("About Project");
        setSize(500, 620);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(225, 242, 255));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(180, 205, 230)));
        panel.setBounds(40, 35, 405, 470);
        add(panel);

        JLabel titleLabel = new JLabel("About This Project");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(10, 45, 90));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(40, 25, 320, 35);
        panel.add(titleLabel);

        JTextArea aboutText = new JTextArea();
        aboutText.setText(
            "Project Name:\n" +
            "Flappy Bird Game with Login,\n" +
            "Registration and Leaderboard System\n" +
            "Language: Java\n" +
            "GUI: Swing and AWT\n" +
            "Storage: Text File\n" +
            "Main Concepts: OOP, File Handling,\n" +
            "Event Handling, Timer\n" +
            "This project is made as a 2nd semester\n" +
            "Java final project.\n" +
            "Main Features:\n" +
            "Login System\n" +
            "Registration System\n" +
            "Difficulty Level\n" +
            "Flappy Bird Gameplay\n" +
            "Score Saving\n" +
            "Leaderboard System\n" +
            "Instructions Page\n" +
            "About Page\n" +
            "Pause and Resume\n" +
            "Back to Menu Option"
        );

        aboutText.setFont(new Font("Arial", Font.PLAIN, 14));
        aboutText.setForeground(new Color(10, 45, 90));
        aboutText.setBackground(Color.WHITE);
        aboutText.setEditable(false);
        aboutText.setFocusable(false);
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setBounds(45, 80, 320, 360);
        panel.add(aboutText);

        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBackground(new Color(50, 130, 245));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBounds(185, 525, 120, 35);
        closeButton.addActionListener(this);
        add(closeButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            dispose();
        }
    }
}