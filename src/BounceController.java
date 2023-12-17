import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BounceController extends KeyAdapter {
    private FrameController FC;
    private Ball ball;
    private Pedal pedal;
    private AnimationWriter writer;

    // Constructor (생성자)
    public BounceController(Ball MB, AnimationWriter AW, Pedal Pd, FrameController fc) {
        ball = MB; writer = AW; pedal = Pd; FC = fc;
        writer.addKeyListener(this);                        // KeyListener이 있어야 key event를 받아올 수 있음
        writer.setFocusable(true);                          // 포커스를 여기로 설정
    }

    public void runAnimation() {    // main 다음 실행, 공에게 1시간 단위만큼 움직이라 지시, 뷰에게 현재 공의 상태를 다시 그리라고 지시
        int time_unit = 1;
        int painting_delay = 20;
        while (ball.non_death())    // 살아있을 때만 실행
        {
            delay(painting_delay);
            ball.move(time_unit);
            writer.repaint();

            if(ball.non_death() == false) {
                FC.game2main();
                JOptionPane.showMessageDialog(null, "your Score is " + writer.getScore() + ".\n");
                Record();               // 결과를 기록하고
                writer.ResetBricks();   // 벽돌초기화
                pedal.setGame(150);     // 페달초기화
                ball.gameEnd();         // 볼초기화
            }
        }
    }

    private void Record() {
        String user = FC.getUserName() + "/" + writer.getScore();
        System.out.println(user);

        if (FC.getDifficulty())
            return;

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
