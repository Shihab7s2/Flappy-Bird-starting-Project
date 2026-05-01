import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int width = 360;
    int height = 640;
    Image bg, birdImg, topImg, bottomImg;
    int bx = 50, by = 250;
    int bw = 34, bh = 24;

    int velY = 0;
    int gravity = 1;

    int pipeW = 64, pipeH = 512;
    int speed = -4;

    ArrayList<Pipe> list = new ArrayList<>();

    Timer loop;
    Timer pipeLoop;
  boolean over = false;
    double score = 0;

    class Pipe {
        int x, y;
        Image img;
        boolean done = false;

     Pipe(int x, int y, Image img) {
            this.x = x;
            this.y = y;
            this.img = img;
        }
    }
     FlappyBird() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        addKeyListener(this);

        bg = new ImageIcon("flappybirdbg.png").getImage();
        birdImg = new ImageIcon("flappybird.png").getImage();
        topImg = new ImageIcon("toppipe.png").getImage();
        bottomImg = new ImageIcon("bottompipe.png").getImage();

        loop = new Timer(1000/60, this);
        loop.start();

        pipeLoop = new Timer(1800, new ActionListener() {
            public void actionPerformed(ActionEvent e) { addPipe();
            }
        });
        pipeLoop.start();
    }
 void addPipe() {
        int y = -200 - (int)(Math.random() * 150);
        int gap = 160;

        list.add(new Pipe(width, y, topImg));
        list.add(new Pipe(width, y + pipeH + gap, bottomImg));
    } public void paintComponent(Graphics g) {
        super.paintComponent(g);
     g.drawImage(bg, 0, 0, width, height, null);
        g.drawImage(birdImg, bx, by, bw, bh, null);

        for (Pipe p : list) {
            g.drawImage(p.img, p.x, p.y, pipeW, pipeH, null);
        }
    g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 26));

        if (over) {
            g.drawString("Game Over", 100, 280);
            g.drawString("Score: " + (int)score, 115, 320);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Press SPACE", 110, 360);
        } else {
            g.drawString("Score: " + (int)score, 15, 35);
        }
    }

    void updateGame() {

        if (over) return;
     velY += gravity;
        by += velY;
     if (by < 0) {
            by = 0;
            velY = 0;
        }
    if (by > height - bh) {
            over = true;
        }
    for (Pipe p : list) {
            p.x += speed;

    if (!p.done && bx > p.x + pipeW) {
                score += 0.5;
                p.done = true;
            }
     if (hit(p)) {
                over = true;
            }
        }
     if (over) {
            pipeLoop.stop();
        }
    }
     boolean hit(Pipe p) {
        if (bx < p.x + pipeW &&
            bx + bw > p.x &&
            by < p.y + pipeH &&
            by + bh > p.y) {
            return true;
        }
        return false;
    }
     void reset() {
        by = 250;
        velY = 0;
        list.clear();
        score = 0;
        over = false;
        pipeLoop.start();
    }   public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }
     public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (over) {
                reset();
            } else {
                velY = -10;
            }
        }
    }   
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}