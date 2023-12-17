import java.awt.*;


public class Ball {
    private int x_pos, y_pos, radius;   // x위치, y위치, 반지름
    private int x_velocity;         // x축 속도
    private int y_velocity;         // y축 속도
    private boolean now = true;
    private Box container;
    private Pedal pedal;

    // constructor
    public Ball(int x, int y, int r, Box box, Pedal Pd, FrameController fc) {
        x_pos = x; y_pos = y; radius = r; container = box; pedal = Pd; //FC = fc;
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
        
        if (x_pos + radius >= pedal.getX_pos() && x_pos - radius <= pedal.getX_pos() + pedal.get_width()
                && y_pos + radius >= container.SizeOf_height() - 100 && y_pos <= container.SizeOf_height() - 100 + pedal.get_height()) { // pedal과 닿으면 튕기기

            if (y_velocity > 0) // pedal 안에 갇힘 방지
                y_velocity = -y_velocity;
        }

    }

    // 속도변경
    public void x_vel(int x) { x_velocity = x; }
    public void y_vel(int y) { y_velocity = y; }

    public Rectangle getBounds() {
        return new Rectangle(x_pos, y_pos, 2 * this.radius, 2 * this.radius);
    }
    public int getX_velocity() {
        return x_velocity;
    }
    public int getY_velocity() {
        return y_velocity;
    }

    // 죽었는지 확인
    public boolean non_death() {
        if (container.outVerticalContact(y_pos)) {  // 박스를 나가면 게임 종료
            now = false;
        }
        else {
            now = true;
        }
        return now;
    }

    public void gameStart() {
        x_velocity = 15; y_velocity = 6; x_pos = 200; y_pos = 400; now = true;
    }
    public void gameEnd() {
        x_velocity = 0; y_velocity = 0; x_pos = 200; y_pos = 400; now = false;
    }
    
}
