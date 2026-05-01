import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int boardWidth = 360;
    int boardHeight = 640;

    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    int birdX = 50;
    int birdY = 250;
    int birdWidth = 34;
    int birdHeight = 24;

    int velocityY = 0;
    int gravity = 1;

    int pipeWidth = 64;
    int pipeHeight = 512;
    int pipeSpeed = -4;

    ArrayList<Pipe> pipes = new ArrayList<>();

    Timer gameLoop;
    Timer pipeTimer;

    boolean gameOver = false;
    double score = 0;

    class Pipe {
        int x;
        int y;
        Image img;
        boolean passed = false;

        Pipe(int x, int y, Image img) {
            this.x = x;
            this.y = y;
            this.img = img;
        }
    }

    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        backgroundImg = new ImageIcon("flappybirdbg.png").getImage();
        birdImg = new ImageIcon("flappybird.png").getImage();
        topPipeImg = new ImageIcon("toppipe.png").getImage();
        bottomPipeImg = new ImageIcon("bottompipe.png").getImage();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        pipeTimer = new Timer(1800, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPipes();
            }
        });
        pipeTimer.start();
    }

    public void addPipes() {
        int randomY = -200 - (int)(Math.random() * 150);
        int gap = 160;

        Pipe topPipe = new Pipe(boardWidth, randomY, topPipeImg);
        Pipe bottomPipe = new Pipe(boardWidth, randomY + pipeHeight + gap, bottomPipeImg);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(birdImg, birdX, birdY, birdWidth, birdHeight, null);

        for (Pipe pipe : pipes) {
            g.drawImage(pipe.img, pipe.x, pipe.y, pipeWidth, pipeHeight, null);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));

        if (gameOver) {
            g.drawString("Game Over", 100, 280);
            g.drawString("Score: " + (int) score, 115, 320);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Press SPACE to Restart", 95, 360);
        } else {
            g.drawString("Score: " + (int) score, 15, 35);
        }
    }

    public void move() {
        if (gameOver) {
            return;
        }

        velocityY += gravity;
        birdY += velocityY;

        if (birdY < 0) {
            birdY = 0;
            velocityY = 0;
        }

        if (birdY > boardHeight - birdHeight) {
            gameOver = true;
        }

        for (Pipe pipe : pipes) {
            pipe.x += pipeSpeed;

            if (!pipe.passed && birdX > pipe.x + pipeWidth) {
                score += 0.5;
                pipe.passed = true;
            }

            if (collision(pipe)) {
                gameOver = true;
            }
        }

        if (gameOver) {
            pipeTimer.stop();
        }
    }

    public boolean collision(Pipe pipe) {
        return birdX < pipe.x + pipeWidth &&
               birdX + birdWidth > pipe.x &&
               birdY < pipe.y + pipeHeight &&
               birdY + birdHeight > pipe.y;
    }

    public void resetGame() {
        birdY = 250;
        velocityY = 0;
        pipes.clear();
        score = 0;
        gameOver = false;
        pipeTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameOver) {
                resetGame();
            } else {
                velocityY = -10;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}