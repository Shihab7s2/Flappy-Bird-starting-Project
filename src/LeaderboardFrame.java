import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LeaderboardFrame extends JFrame implements ActionListener {
    JButton backButton;
    String username;

    public LeaderboardFrame(String username) {
        this.username = username;

        setTitle("Leaderboard");
        setSize(420, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 245, 255));

        JLabel titleLabel = new JLabel("Leaderboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setBounds(130, 25, 200, 35);
        add(titleLabel);

        JTextArea scoreArea = new JTextArea();
        scoreArea.setEditable(false);
        scoreArea.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(scoreArea);
        scrollPane.setBounds(60, 80, 300, 250);
        add(scrollPane);

        ScoreManager scoreManager = new ScoreManager();
        ArrayList<String> scores = scoreManager.getScores();

        if (scores.size() == 0) {
            scoreArea.setText("No scores saved yet.");
        } else {
            for (int i = 0; i < scores.size(); i++) {
                scoreArea.append((i + 1) + ". " + scores.get(i) + "\n");
            }
        }

        backButton = new JButton("Back");
        backButton.setBounds(150, 350, 120, 35);
        backButton.addActionListener(this);
        add(backButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose();
        }
    }
}
