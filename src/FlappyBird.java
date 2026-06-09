import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 360;
    int boardHeight = 640;

    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    Bird bird;

    int pipeWidth = 64;
    int pipeHeight = 512;
    int pipeX = boardWidth;
    int gap = 160;
    int pipeSpeed = -4;
    int pipeDelay = 1600;

    ArrayList<Pipe> pipes;
    Random random;

    Timer gameLoop;
    Timer pipeTimer;

    boolean gameOver = false;
    boolean scoreSaved = false;
    boolean gameOverSoundPlayed = false;
    boolean paused = false;
    int score = 0;
    String username;
    String difficulty;

    // Countdown state
    boolean countdownActive = true;
    int countdownValue = 3;
    boolean showGo = false;
    Timer countdownTimer;
    SoundManager soundManager;

    public FlappyBird(String username) {
        this(username, "Medium");
    }

    public FlappyBird(String username, String difficulty) {
        this.username = username;
        this.difficulty = difficulty;
        soundManager = new SoundManager();

        setDifficulty(difficulty);
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        backgroundImg = loadImage("flappybirdbg.png");
        birdImg = loadImage("flappybird.png");
        topPipeImg = loadImage("toppipe.png");
        bottomPipeImg = loadImage("bottompipe.png");

        bird = new Bird(50, 250, 34, 24, birdImg);

        pipes = new ArrayList<Pipe>();
        random = new Random();

        // Start the render loop (for drawing the countdown)
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        // Pipe timer is created but NOT started until countdown finishes
        pipeTimer = new Timer(pipeDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver && !paused) {
                    placePipes();
                }
            }
        });

        // Start the countdown
        startCountdown();
    }

    public void setDifficulty(String difficulty) {
        if (difficulty.equals("Easy")) {
            gap = 190;
            pipeSpeed = -3;
            pipeDelay = 1800;
        } else if (difficulty.equals("Hard")) {
            gap = 135;
            pipeSpeed = -5;
            pipeDelay = 1300;
        } else {
            gap = 160;
            pipeSpeed = -4;
            pipeDelay = 1600;
        }
    }

    public void startCountdown() {
        countdownActive = true;
        countdownValue = 3;
        showGo = false;

        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (countdownValue > 1) {
                    countdownValue--;
                } else if (countdownValue == 1) {
                    countdownValue = 0;
                    showGo = true;
                } else {
                    // "GO!" has been shown for 1 second, start the game
                    countdownTimer.stop();
                    countdownActive = false;
                    showGo = false;
                    placePipes();
                    pipeTimer.start();
                }
                repaint();
            }
        });
        countdownTimer.start();
    }

    public Image loadImage(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            file = new File("src" + File.separator + fileName);
        }
        return new ImageIcon(file.getPath()).getImage();
    }

    public void placePipes() {
        int randomY = -pipeHeight / 4 - random.nextInt(pipeHeight / 2);

        Pipe topPipe = new Pipe(pipeX, randomY, pipeWidth, pipeHeight, topPipeImg, pipeSpeed);
        Pipe bottomPipe = new Pipe(pipeX, randomY + pipeHeight + gap, pipeWidth, pipeHeight, bottomPipeImg, pipeSpeed);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
        bird.draw(g);

        for (int i = 0; i < pipes.size(); i++) {
            pipes.get(i).draw(g);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));

        if (gameOver) {
            g.setColor(new Color(0, 0, 0, 170));
            g.fillRoundRect(35, 205, 290, 250, 25, 25);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("Game Over", 95, 250);
            g.setFont(new Font("Arial", Font.BOLD, 21));
            g.drawString("Score: " + score, 135, 295);
            g.drawString("Level: " + difficulty, 115, 330);
            g.drawString("Score Saved", 115, 365);
            g.setFont(new Font("Arial", Font.BOLD, 17));
            g.drawString("R = Restart", 125, 405);
            g.drawString("ESC = Back to Menu", 90, 432);
        } else {
            g.drawString(String.valueOf(score), 170, 50);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Level: " + difficulty, 15, 30);
            if (!countdownActive) {
                g.drawString("P = Pause", 265, 30);
            }

            if (countdownActive) {
                // Semi-transparent overlay
                g.setColor(new Color(0, 0, 0, 120));
                g.fillRoundRect(110, 240, 140, 120, 25, 25);
                g.setColor(Color.WHITE);
                if (showGo) {
                    g.setFont(new Font("Arial", Font.BOLD, 60));
                    g.drawString("GO!", 115, 320);
                } else {
                    g.setFont(new Font("Arial", Font.BOLD, 72));
                    g.drawString(String.valueOf(countdownValue), 155, 330);
                }
            } else if (paused) {
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRoundRect(55, 235, 250, 135, 25, 25);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 30));
                g.drawString("Paused", 125, 280);
                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.drawString("Press P to Resume", 105, 320);
                g.drawString("ESC = Back to Menu", 95, 345);
            }
        }
    }

    public void move() {
        bird.move();

        if (bird.getY() < 0 || bird.getY() + bird.height > boardHeight) {
            gameOver = true;
        }

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.move();

            if (i % 2 == 0 && !pipe.isPassed() && pipe.getX() + pipeWidth < bird.getX()) {
                score++;
                pipe.setPassed(true);
            }

            if (collision(bird, pipe)) {
                gameOver = true;
            }
        }

        for (int i = 0; i < pipes.size(); i++) {
            if (pipes.get(i).isOffScreen()) {
                pipes.remove(i);
                i--;
            }
        }
    }

    public boolean collision(GameObject a, GameObject b) {
        return a.getBounds().intersects(b.getBounds());
    }

    public void saveScoreOnce() {
        if (!scoreSaved) {
            ScoreManager scoreManager = new ScoreManager();
            scoreManager.saveScore(username, score);
            scoreSaved = true;
        }
    }

    public void restartGame() {
        bird.reset();
        pipes.clear();
        score = 0;
        gameOver = false;
        scoreSaved = false;
        gameOverSoundPlayed = false;
        paused = false;
        gameLoop.start();
        // Restart with countdown instead of immediately spawning pipes
        startCountdown();
    }


    public void backToMenu() {
        gameLoop.stop();
        pipeTimer.stop();
        if (countdownTimer != null) {
            countdownTimer.stop();
        }
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
        new MenuFrame(username);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (countdownActive) {
            // During countdown, only repaint (bird visible, no movement)
            repaint();
            return;
        }
        if (!gameOver) {
            if (!paused) {
                move();
            }
            repaint();
        } else {
            saveScoreOnce();
            if (!gameOverSoundPlayed) {
                soundManager.playGameOverSound();
                gameOverSoundPlayed = true;
            }
            gameLoop.stop();
            pipeTimer.stop();
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver && !paused && !countdownActive) {
            bird.jump();
            soundManager.playJumpSound();
        }

        if (e.getKeyCode() == KeyEvent.VK_P && !gameOver && !countdownActive) {
            paused = !paused;
            repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restartGame();
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            backToMenu();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
