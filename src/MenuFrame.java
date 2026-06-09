import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuFrame extends JFrame implements ActionListener {
    String username;
    String selectedDifficulty = "Medium";

    JButton easyButton;
    JButton mediumButton;
    JButton hardButton;
    JButton startButton;
    JButton leaderboardButton;
    JButton instructionsButton;
    JButton aboutButton;
    JButton logoutButton;
    JButton exitButton;

    Color backgroundColor = new Color(225, 241, 255);
    Color cardColor = new Color(255, 255, 255);
    Color primaryColor = new Color(45, 125, 255);
    Color darkTextColor = new Color(25, 45, 75);
    Color normalButtonColor = new Color(245, 250, 255);
    Color selectedButtonColor = new Color(45, 125, 255);
    Color dangerColor = new Color(230, 75, 85);

    public MenuFrame(String username) {
        this.username = username;

        setTitle("Flappy Bird Menu");
        setSize(450, 620);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(backgroundColor);

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(cardColor);
        cardPanel.setBounds(35, 25, 365, 525);
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(190, 215, 240), 1));
        add(cardPanel);

        JLabel titleLabel = new JLabel("Flappy Bird Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(darkTextColor);
        titleLabel.setBounds(35, 25, 295, 35);
        cardPanel.add(titleLabel);

        JLabel welcomeLabel = new JLabel("Welcome, " + username, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        welcomeLabel.setForeground(new Color(70, 90, 120));
        welcomeLabel.setBounds(35, 65, 295, 25);
        cardPanel.add(welcomeLabel);

        JLabel difficultyLabel = new JLabel("Choose Level", SwingConstants.CENTER);
        difficultyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        difficultyLabel.setForeground(darkTextColor);
        difficultyLabel.setBounds(35, 110, 295, 25);
        cardPanel.add(difficultyLabel);

        easyButton = new JButton("Easy");
        easyButton.setBounds(30, 150, 95, 36);
        easyButton.addActionListener(this);
        cardPanel.add(easyButton);

        mediumButton = new JButton("Medium");
        mediumButton.setBounds(135, 150, 95, 36);
        mediumButton.addActionListener(this);
        cardPanel.add(mediumButton);

        hardButton = new JButton("Hard");
        hardButton.setBounds(240, 150, 95, 36);
        hardButton.addActionListener(this);
        cardPanel.add(hardButton);

        startButton = new JButton("Start Game");
        startButton.setBounds(75, 215, 215, 38);
        startButton.addActionListener(this);
        cardPanel.add(startButton);

        leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.setBounds(75, 262, 215, 38);
        leaderboardButton.addActionListener(this);
        cardPanel.add(leaderboardButton);

        instructionsButton = new JButton("Instructions");
        instructionsButton.setBounds(75, 309, 215, 38);
        instructionsButton.addActionListener(this);
        cardPanel.add(instructionsButton);

        aboutButton = new JButton("About Project");
        aboutButton.setBounds(75, 356, 215, 38);
        aboutButton.addActionListener(this);
        cardPanel.add(aboutButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(75, 415, 215, 38);
        logoutButton.addActionListener(this);
        cardPanel.add(logoutButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(75, 462, 215, 38);
        exitButton.addActionListener(this);
        cardPanel.add(exitButton);

        designButton(easyButton, false);
        designButton(mediumButton, false);
        designButton(hardButton, false);
        designButton(startButton, true);
        designButton(leaderboardButton, false);
        designButton(instructionsButton, false);
        designButton(aboutButton, false);
        designButton(logoutButton, false);
        designDangerButton(exitButton);

        updateDifficultyButtons();
        setVisible(true);
    }

    public void designButton(JButton button, boolean primary) {
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(new Color(120, 170, 230), 1));

        if (primary) {
            button.setBackground(primaryColor);
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(normalButtonColor);
            button.setForeground(darkTextColor);
        }
    }

    public void designDangerButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(dangerColor);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(200, 50, 60), 1));
    }

    public void updateDifficultyButtons() {
        designButton(easyButton, false);
        designButton(mediumButton, false);
        designButton(hardButton, false);

        if (selectedDifficulty.equals("Easy")) {
            easyButton.setBackground(selectedButtonColor);
            easyButton.setForeground(Color.WHITE);
        } else if (selectedDifficulty.equals("Hard")) {
            hardButton.setBackground(selectedButtonColor);
            hardButton.setForeground(Color.WHITE);
        } else {
            mediumButton.setBackground(selectedButtonColor);
            mediumButton.setForeground(Color.WHITE);
        }
    }

    public void openGame() {
        JFrame gameFrame = new JFrame("Flappy Bird - " + selectedDifficulty);
        FlappyBird flappyBird = new FlappyBird(username, selectedDifficulty);

        gameFrame.add(flappyBird);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setVisible(true);
        dispose();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                flappyBird.requestFocusInWindow();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == easyButton) {
            selectedDifficulty = "Easy";
            updateDifficultyButtons();
        }

        if (e.getSource() == mediumButton) {
            selectedDifficulty = "Medium";
            updateDifficultyButtons();
        }

        if (e.getSource() == hardButton) {
            selectedDifficulty = "Hard";
            updateDifficultyButtons();
        }

        if (e.getSource() == startButton) {
            openGame();
        }

        if (e.getSource() == leaderboardButton) {
            new LeaderboardFrame(username);
        }

        if (e.getSource() == instructionsButton) {
            new InstructionsFrame();
        }

        if (e.getSource() == aboutButton) {
            new AboutFrame();
        }

        if (e.getSource() == logoutButton) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                dispose();
                new LoginFrame();
            }
        }

        if (e.getSource() == exitButton) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}
