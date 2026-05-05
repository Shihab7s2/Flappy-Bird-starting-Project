import java.awt.*;

public class Pipe extends GameObject {
    private int velocityX;
    private boolean passed;

    public Pipe(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        velocityX = -4;
        passed = false;
    }

    public void move() {
        x += velocityX;
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
