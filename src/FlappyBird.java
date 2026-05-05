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

    ArrayList<Pipe> pipes;
    Random random;

    Timer gameLoop;
    Timer pipeTimer;

    boolean gameOver = false;
    int score = 0;

    public FlappyBird() {
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

        placePipes();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        pipeTimer = new Timer(1600, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    placePipes();
                }
            }
        });
        pipeTimer.start();
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

        Pipe topPipe = new Pipe(pipeX, randomY, pipeWidth, pipeHeight, topPipeImg);
        Pipe bottomPipe = new Pipe(pipeX, randomY + pipeHeight + gap, pipeWidth, pipeHeight, bottomPipeImg);

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
        g.setFont(new Font("Arial", Font.BOLD, 32));

        if (gameOver) {
            g.drawString("Game Over", 95, 260);
            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.drawString("Score: " + score, 135, 310);
            g.drawString("Press R to Restart", 85, 360);
        } else {
            g.drawString(String.valueOf(score), 170, 50);
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

    public void restartGame() {
        bird.reset();
        pipes.clear();
        score = 0;
        gameOver = false;
        placePipes();
        gameLoop.start();
        pipeTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            repaint();
        } else {
            gameLoop.stop();
            pipeTimer.stop();
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            bird.jump();
        }

        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restartGame();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
