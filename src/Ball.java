import java.awt.*;
import java.util.*;

public class Ball extends Rectangle {

    Random random;
    int vel_x;
    int vel_y;
    int initialSpeed;

    Ball(int x, int y, int width, int height, int speed) {
        super(x, y, width, height);

        initialSpeed = speed;
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection * initialSpeed);

        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection * initialSpeed);
    }

    public void setXDirection(int randomX) {
        vel_x = randomX;
    }

    public void setYDirection(int randomY) {
        vel_y = randomY;
    }

    public void move() {
        x += vel_x;
        y += vel_y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, height, width);
    }
}
