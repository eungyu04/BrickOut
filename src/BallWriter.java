import java.awt.*;

public class BallWriter {
    private Ball ball;
    private Color balls_color;

    // Constructor (생성자)
    public BallWriter(Ball x, Color c) {
        ball = x;
        balls_color = c;
    }

    // setter
    public void paint(Graphics g) {
        g.setColor(balls_color);        // 색 지정
        int radius = ball.radiusOf();   // 반지름
        g.fillOval(ball.xPosition() - radius, ball.yPosition() - radius, radius *2, radius *2); // 공 그리기
    }

}
