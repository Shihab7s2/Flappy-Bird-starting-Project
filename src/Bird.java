import java.awt.*;

public class Bird extends GameObject {
    private int velocityY;
    private int gravity;

    public Bird(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        velocityY = 0;
        gravity = 1;
    }

    public void move() {
        velocityY += gravity;
        y += velocityY;
    }

    public void jump() {
        velocityY = -10;
    }

    public void reset() {
        y = 250;
        velocityY = 0;
    }

    public int getY() {
        return y;
    }
}
