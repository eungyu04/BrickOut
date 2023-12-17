import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BrickWriter extends JFrame {
    private List<Brick> bricks;

    public BrickWriter() {
        bricks = new ArrayList<>();
        initializeBricks();
    }

    private void initializeBricks() {
        int brickWidth = 84;
        int brickHeight = 48;
        int rows = 4;  // 4행
        int columns = 5;  // 5열

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int x = col * (brickWidth + 5) + 10;
                int y = row * (brickHeight + 5) + 15;
                Color color = Color.white; // 벽돌의 색상을 초록색으로 설정

                Brick brick = new Brick(x, y, brickWidth, brickHeight, color);
                bricks.add(brick);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        for (Brick brick : bricks) {
            if (brick.isVisible()) {
                g.setColor(brick.getColor());
                g.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
                g.setColor(brick.getColor());
                g.drawRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
            }
        }
    }


}