import javax.swing.*;

public class Ball {
    private int x_pos, y_pos, radius;   // x위치, y위치, 반지름
    private int x_velocity;         // x축 속도
    private int y_velocity;         // y축 속도
    private Box container;
    private Pedal pedal;

    // constructor
    public Ball(int x, int y, int r, Box box, Pedal Pd) {
        x_pos = x; y_pos = y; radius = r; container = box; pedal = Pd;
    }

    // getter
    public int xPosition() {
        return x_pos;
    }
    public int yPosition() {
        return y_pos;
    }
    public int radiusOf() {
        return radius;
    }

    // setter
    public void move(int time_units) {      // 공 움직임
        x_pos = x_pos + x_velocity * time_units;
        if (container.inHorizontalContact(x_pos))   // 벽 튕기기
            x_velocity = -x_velocity;

        y_pos = y_pos + y_velocity * time_units;
        if (container.inVerticalContact(y_pos))     // 벽 튕기기
            y_velocity = -y_velocity;

        if (container.outVerticalContact(y_pos)) {      // 게임 종료
            JOptionPane.showMessageDialog(null, "Game Over \nGo to Ranking");
            System.exit(0);
        }

        if (x_pos + radius >= pedal.getX_pos() && x_pos - radius <= pedal.getX_pos() + pedal.get_width()
                && y_pos + radius >= container.SizeOf_height() - 100 && y_pos <= container.SizeOf_height() - 100 + pedal.get_height()) { // pedal과 닿으면 튕기기

            if (y_velocity > 0) // pedal 안에 갇힘 방지
                y_velocity = -y_velocity;
        }
    }

    public void gameStart() {
        x_velocity = 15; y_velocity = 6; x_pos = 0; y_pos = 0;
    }
    public void gameEnd() {

    }

}
