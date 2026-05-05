import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuFrame extends JFrame implements ActionListener {
    String username;
    JButton startButton;
    JButton logoutButton;
    JButton exitButton;

    public MenuFrame(String username) {
        this.username = username;

        setTitle("Flappy Bird Menu");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Flappy Bird Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(90, 25, 250, 35);
        add(titleLabel);

        JLabel welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        welcomeLabel.setBounds(130, 70, 200, 25);
        add(welcomeLabel);

        startButton = new JButton("Start Game");
        startButton.setBounds(125, 115, 140, 35);
        startButton.addActionListener(this);
        add(startButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(125, 160, 140, 35);
        logoutButton.addActionListener(this);
        add(logoutButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(125, 205, 140, 35);
        exitButton.addActionListener(this);
        add(exitButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            JFrame gameFrame = new JFrame("Flappy Bird");
            FlappyBird flappyBird = new FlappyBird();

            gameFrame.add(flappyBird);
            gameFrame.pack();
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setResizable(false);
            gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            gameFrame.setVisible(true);

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    flappyBird.requestFocusInWindow();
                }
            });
        }

        if (e.getSource() == logoutButton) {
            dispose();
            new LoginFrame();
        }

        if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}
