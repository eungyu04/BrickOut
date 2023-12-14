import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BounceController extends KeyAdapter {
    private MovingBall ball;
    private Pedal pedal;
    private AnimationWriter writer;

    // Constructor (생성자)
    public BounceController(MovingBall MB, AnimationWriter AW, Pedal Pd) {
        ball = MB; writer = AW; pedal = Pd;
        writer.addKeyListener(this);                    // KeyListener이 있어야 key event를 받아올 수 있음
        writer.setFocusable(true);                            // 포커스를 여기로 설정
    }

    // setter
    public void runAnimation() {    // main 다음 실행, 공에게 1시간 단위만큼 움직이라 지시, 뷰에게 현재 공의 상태를 다시 그리라고 지시
        int time_unit = 1;
        int painting_delay = 20;
        while (true)
        {
            delay(painting_delay);
            ball.move(time_unit);
            writer.repaint();
        }
    }

    // key event -> KeyPressed() 정의
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("KeyPressed success");
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_RIGHT) {     // Right
            pedal.moveRight();
        }
        if (keyCode == KeyEvent.VK_LEFT) {      // Left
            pedal.moveLeft();
        }
    }

    private void delay(int how_long) {
        try {
            Thread.sleep(how_long);
        } catch (InterruptedException e) { }
    }
}
