import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyFrame {
    private AnimationWriter AW;
    private MovingBall MB;

    public DifficultyFrame(AnimationWriter aw, MovingBall mb) {
        this.AW = aw;
        this.MB = mb;

        JFrame difficulty_frame = new JFrame();                 // JFrame 객체 생성
        difficulty_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        difficulty_frame.setTitle("DIFFICULTY");                // title
        difficulty_frame.setSize( 300, 300);       // frame size
        difficulty_frame.setLocation(600,200);            // Location

        JPanel difficultly_panel = new JPanel();                // JPanel 객체 생성
        difficultly_panel.setBackground(Color.black);

        // slow button (easy)
        JButton easyButton = new JButton("Easy");
        easyButton.setBorder(BorderFactory.createLineBorder(Color.black, 1));    // 마우스 가져다 댔을 때 변화 X
        easyButton.setPreferredSize(new Dimension(150, 60));            // 버튼 크기
        easyButton.setBackground(Color.gray);                                       // 버튼 배경 색
        difficultly_panel.add(easyButton);

        // fast button (hard)
        JButton hardButton = new JButton("Hard");
        hardButton.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        hardButton.setPreferredSize(new Dimension(150, 60));
        hardButton.setBackground(Color.gray);
        difficultly_panel.add(hardButton);

        // button Event
        easyButton.addActionListener(new ActionListener() {         // easybutton 동작 명령 Override
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty_frame.setVisible(false);
                AW.setvisible(true);
                MB.x_vel(5);
                MB.y_vel(2);
            }
        });
        hardButton.addActionListener(new ActionListener() {         // hardbutton 동작 명령 Override
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty_frame.setVisible(false);
                AW.setvisible(true);
                MB.x_vel(15);
                MB.y_vel(6);
            }
        });

        difficulty_frame.add(difficultly_panel);                // frame에 panel 추가
        difficulty_frame.setVisible(true);                      // 보이게 함
    }



}
