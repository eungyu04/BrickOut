public class MovingBall {
    private int x_pos, y_pos, radius;   // x위치, y위치, 반지름
    private int x_velocity;         // x축 속도
    private int y_velocity;         // y축 속도
    private Box container;
    private Pedal pedal;

    // constructor
    public MovingBall(int x, int y, int r, Box box, Pedal Pd) {
        x_pos = x;
        y_pos = y;
        radius = r;
        container = box;
        pedal = Pd;
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
        if (container.inHorizontalContact(x_pos))   // 벽튕기기
            x_velocity = -x_velocity;

        y_pos = y_pos + y_velocity * time_units;
        if (container.inVerticalContact(y_pos))     // 벽튕기기
            y_velocity = -y_velocity;
        if (x_pos >= pedal.getX_pos() && x_pos <= pedal.getX_pos() + pedal.get_width() && y_pos >= container.SizeOf() - 100) // pedal과 닿으면 튕기기
            y_velocity = -y_velocity;

    }

    // 속도변경
    public void x_vel(int x) { x_velocity = x; }
    public void y_vel(int y) { y_velocity = y; }

}