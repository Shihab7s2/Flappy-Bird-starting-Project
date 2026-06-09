import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InstructionsFrame extends JFrame implements ActionListener {
    JButton closeButton;

    public InstructionsFrame() {
        setTitle("Instructions");
        setSize(460, 520);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(225, 242, 255));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(180, 205, 230)));
        panel.setBounds(35, 35, 390, 380);
        add(panel);

        JLabel titleLabel = new JLabel("Game Instructions");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(10, 45, 90));
        titleLabel.setBounds(75, 25, 250, 35);
        panel.add(titleLabel);

        JTextArea instructionText = new JTextArea();
        instructionText.setText(
            "Controls:\n" +
            "SPACE  = Jump\n" +
            "P      = Pause / Resume\n" +
            "R      = Restart after game over\n" +
            "ESC    = Back to menu\n" +
            "Rules:\n" +
            "Avoid the pipes.\n" +
            "Score increases when the bird passes a pipe.\n" +
            "The game ends when the bird touches a pipe or falls down.\n" +
            "Difficulty Levels:\n" + "Easy   = Slower pipes and bigger gap\n" +
            "Medium = Normal speed and normal gap\n" +
            "Hard   = Faster pipes and smaller gap"
        );
        instructionText.setFont(new Font("Arial", Font.PLAIN, 14));
        instructionText.setForeground(new Color(10, 45, 90));
        instructionText.setBackground(Color.WHITE);
        instructionText.setEditable(false);
        instructionText.setFocusable(false);
        instructionText.setLineWrap(true);
        instructionText.setWrapStyleWord(true);
        instructionText.setBounds(45, 75, 305, 285);
        panel.add(instructionText);

        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBackground(new Color(50, 130, 245));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBounds(165, 430, 120, 35);
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