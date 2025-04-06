import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    static final int WIDTH = 1000;
    static final int HEIGHT = 555;
    static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHT);
    static final int BALL_DIAMETER = 20;

    static final int P_WIDTH = 25;
    static final int P_HEIGHT = 100;

    Thread gameThread;
    Random random;
    Paddle p1;
    Paddle p2;
    Ball ball;
    Score score;
    int speed;

    public GamePanel(int speed) {
        this.speed = speed;
        newPaddles();
        newBall(speed);
        score = new Score(WIDTH, HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(DIMENSION);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall(int speed) {
        random = new Random();
        ball = new Ball((WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(HEIGHT - BALL_DIAMETER), BALL_DIAMETER,
                BALL_DIAMETER, speed);
    }

    public void newPaddles() {
        p1 = new Paddle(0, (HEIGHT / 2) - (P_HEIGHT / 2), P_WIDTH, P_HEIGHT, 1);
        p2 = new Paddle(WIDTH - P_WIDTH, (HEIGHT / 2) - (P_HEIGHT / 2), P_WIDTH, P_HEIGHT, 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        p1.draw(g);
        p2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move() {
        p1.move();
        p2.move();
        ball.move();
    }

    public void checkCollision() {
        if (ball.y <= 0 || ball.y >= HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.vel_y);
        }

        if (ball.intersects(p1)) {
            ball.vel_x = Math.abs(ball.vel_x);
            ball.setXDirection(ball.vel_x);
            ball.setYDirection(ball.vel_y);
        }
        if (ball.intersects(p2)) {
            ball.vel_x = Math.abs(ball.vel_x);
            ball.setXDirection(-ball.vel_x);
            ball.setYDirection(ball.vel_y);
        }

        p1.y = Math.min(Math.max(p1.y, 0), HEIGHT - P_HEIGHT);
        p2.y = Math.min(Math.max(p2.y, 0), HEIGHT - P_HEIGHT);

        if (ball.x <= 0 || ball.x >= WIDTH - BALL_DIAMETER) {
            if (ball.x <= 0) {
                score.player2++;
            } else {
                score.player1++;
            }
            newPaddles();
            newBall(speed);
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            p1.keyPressed(e);
            p2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            p1.keyReleased(e);
            p2.keyReleased(e);
        }
    }
}

