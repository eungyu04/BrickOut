import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DifficultyFrame {
    private AnimationWriter AW;
    private Ball MB;
    private Box box;
    private MainFrame MF;
    private JFrame difficulty_frame;            // 여기서 정의해줘야 다른 함수에서도 사용 가능
    private boolean visible;
    private String user_difficulty;

    public DifficultyFrame(AnimationWriter aw, Ball mb, Box box) {
        this.AW = aw;
        this.MB = mb;
        this.box = box;
        //this.MF = mf;

        Image beforeImage = new ImageIcon(getClass().getResource("/image/background3.png")).getImage();
        Image afterImage = beforeImage.getScaledInstance(box.SizeOf_width(), box.SizeOf_height(), Image.SCALE_SMOOTH);
        ImageIcon backgroundIcon3 = new ImageIcon(afterImage);

        difficulty_frame = new JFrame();                 // JFrame 객체 생성
        difficulty_frame.setTitle("DIFFICULTY");                // title
        difficulty_frame.setSize( box.SizeOf_width(), box.SizeOf_height());        // frame size
        difficulty_frame.setResizable(false);                   // 크기조절
        difficulty_frame.setLocationRelativeTo(null);
        difficulty_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JPanel 객체 생성
        JPanel difficultly_panel = createPanel(backgroundIcon3, 0, 0);
        difficultly_panel.setLayout(new GridBagLayout());

        // slow button (easy)
        JButton easybutton = createButton("/image/easybutton.png", "/image/easybutton2.png");
        addButton(difficultly_panel, easybutton, 0, 0, 220, 0, 0, 0, 1);
        easybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty_frame.setVisible(false);
                AW.setvisible(true);
                user_difficulty = "easy";
                System.out.println(user_difficulty);
                MB.x_vel(5);
                MB.y_vel(2);
            }
        });

        // fast button (hard)
        JButton hardButton = createButton("/image/hardButton.png", "/image/hardButton2.png");
        addButton(difficultly_panel, hardButton, 1, 0, 220, 50, 0, 0, 1);
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty_frame.setVisible(false);
                AW.setvisible(true);
                user_difficulty = "hard";
                System.out.println(user_difficulty);
                MB.x_vel(15);
                MB.y_vel(6);
            }
        });

        // back button
        JButton backButton = createButton("/image/backButton.png", "/image/backButton2.png");
        addButton(difficultly_panel, backButton, 0, 1, 100, 0, 0, 0, 2);
//        backButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                difficulty_frame.setVisible(false);
//                MF.setvisible(true);
//            }
//        });


        difficulty_frame.add(difficultly_panel);                // frame에 panel 추가
        difficulty_frame.setVisible(false);                     // 처음엔 false
    }

    private JPanel createPanel(ImageIcon image, int x, int y) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(image.getImage(), x, y, this);
            }
        };
        return panel;
    }

    private JButton createButton(String image1, String image2) {
        ImageIcon buttonIcon = new ImageIcon(getClass().getResource(image1));
        ImageIcon rolloverIcon = new ImageIcon(getClass().getResource(image2));
        JButton button = new JButton(buttonIcon);
        button.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        button.setRolloverIcon(rolloverIcon);   // 마우스를 댔을 때의 이미지설정
        return button;
    }

    private void addButton(Container panel, Component button, int gridx, int gridy, int top, int left, int bottom, int right, int gridwidth) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;  // 행
        gbc.gridy = gridy;  // 열
        gbc.gridwidth = gridwidth;  // 쓰는 열 개수
        gbc.insets = new Insets(top, left, bottom, right);         // 여백 추가 -> 버튼 위치 조절하려고
        panel.add(button, gbc);
    }
    public void setvisible(boolean n) {
        visible = n;
        difficulty_frame.setVisible(visible);
    }

}
