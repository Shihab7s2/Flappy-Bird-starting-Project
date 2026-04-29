import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int boardWidth = 360;
    int boardHeight = 640;

    Image backgroundImg;
    Image birdImg;

    int birdX = 50;
    int birdY = 250;
    int birdWidth = 34;
    int birdHeight = 24;

    int velocityY = 0;
    int gravity = 1;

    Timer gameLoop;

    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        backgroundImg = new ImageIcon("flappybirdbg.png").getImage();
        birdImg = new ImageIcon("flappybird.png").getImage();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(birdImg, birdX, birdY, birdWidth, birdHeight, null);
    }

    public void move() {
        velocityY += gravity;
        birdY += velocityY;

        if (birdY < 0) {
            birdY = 0;
            velocityY = 0;
        }

        if (birdY > boardHeight - birdHeight) {
            birdY = boardHeight - birdHeight;
            velocityY = 0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -10;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}