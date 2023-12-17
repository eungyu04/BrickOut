import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrickWriter extends JFrame implements ActionListener {
    private Brick[][] bricks;
    private Ball ball;
    private Timer timer;
    private int score = 0;

    public BrickWriter(Ball ball) {
        this.ball = ball;
        bricks = new Brick[5][8];  // 5행 8열의 벽돌 배열로 초기화
        initializeBricks();

        timer = new Timer(5, this);
        timer.start();
    }

    private void initializeBricks() {
        int brickWidth = 48;
        int brickHeight = 48;

        for (int row = 0; row < bricks.length; row++) {
            for (int col = 0; col < bricks[row].length; col++) {
                int x = col * (brickWidth + 8) + 10;
                int y = row * (brickHeight + 5) + 15;
                Color color = Color.WHITE; // 벽돌의 색상을 하얀색으로 설정

                bricks[row][col] = new Brick(x, y, brickWidth, brickHeight, color);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int row = 0; row < bricks.length; row++) {
            for (int col = 0; col < bricks[row].length; col++) {
                if(bricks[row][col].isVisible()) {
                    g.setColor(bricks[row][col].getColor());
                    g.fillRect(bricks[row][col].getX(), bricks[row][col].getY(), bricks[row][col].getWidth(), bricks[row][col].getHeight());
                    g.setColor(bricks[row][col].getColor());
                    g.drawRect(bricks[row][col].getX(), bricks[row][col].getY(), bricks[row][col].getWidth(), bricks[row][col].getHeight());
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkCollision(ball);
        repaint();
    }

    private void checkCollision(Ball ball) {
        Rectangle ballRect = ball.getBounds();

        for (int row = 0; row < bricks.length; row++) {
            for (int col = 0; col < bricks[row].length; col++) {
                if (bricks[row][col].isVisible()) {
                    Rectangle brickRect = bricks[row][col].getBounds();

                    if (ballRect.intersects(brickRect)) {
                        bricks[row][col].setVisible(false);
                        score++;
                        // 충돌 시 꺾임.
                        // 벽돌의 중심 좌표
                        double brickCenterX = brickRect.getX() + brickRect.getWidth() / 2;
                        double brickCenterY = brickRect.getY() + brickRect.getHeight() / 2;

                        // 공의 중심 좌표
                        double ballCenterX = ball.xPosition() + ball.radiusOf();
                        double ballCenterY = ball.yPosition() + ball.radiusOf();

                        // 벽돌의 중심과 공의 중심 간의 상대적인 위치 계산
                        double dx = Math.abs(ballCenterX - brickCenterX);
                        double dy = Math.abs(ballCenterY - brickCenterY);

                        if (dy > dx) {
                            ball.y_vel(-ball.getY_velocity());
                        } else {
                            ball.x_vel(-ball.getX_velocity());
                        }
                    }
                }
            }
        }
    }

    public int getScore() {
        return score;
    }

    // brick 초기화
    public void reset() {
        score = 0;
        for (int row = 0; row < bricks.length; row++) {
            for (int col = 0; col < bricks[row].length; col++) {
                bricks[row][col].setVisible(true);
            }
        }
    }
}