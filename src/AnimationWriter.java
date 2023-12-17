import javax.swing.*;
import java.awt.*;

public class AnimationWriter extends JPanel {
    private BoxWriter box_writer;
    private BallWriter ball_writer;
    private PedalWriter pedal_writer;
    private BrickWriter brick_writer;
    private JFrame my_frame;            // 여기서 정의해줘야 다른 함수에서도 사용 가능
    private boolean visible = false;    // 초기 가시성은 false

    // Constructor (생성자)
    public AnimationWriter(BoxWriter BoW, BallWriter BaW, PedalWriter peW, BrickWriter BrW , int xsize, int ysize) {
        box_writer = BoW;
        ball_writer = BaW;
        pedal_writer = peW;
        brick_writer = BrW;
        
        my_frame = new JFrame();                 // JFrame 객체 생성
        my_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //
        my_frame.getContentPane().add(this);            //
        my_frame.setTitle("GAME");                      // title
        my_frame.setSize(xsize , ysize);                // frame size
        my_frame.setResizable(false);                   // 크기조절 안되게
        my_frame.setLocationRelativeTo(null);           // 위치이동
        my_frame.setVisible(visible);                   // 처음엔 안보임
    }

    public void setvisible(boolean n) {
        visible = n;
        my_frame.setVisible(visible);
    }

    @Override // 패널에 그림
    public void paintComponent(Graphics g) {
        box_writer.paint(g);
        ball_writer.paint(g);
        pedal_writer.paint(g);
        brick_writer.paint(g);
    }

}
