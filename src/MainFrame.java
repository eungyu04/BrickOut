import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private DifficultyFrame DF;
    private Box box;
    private JFrame MainFrame;
    private JPanel mainPanel1;
    private JPanel mainPanel2;
    private boolean visible = true;
    String user_name;

    public MainFrame(DifficultyFrame DF, Box box) {
        this.DF = DF;
        this.box = box;

        // background image1
        Image beforeImage = new ImageIcon(getClass().getResource("/image/background1.png")).getImage();
        Image afterImage = beforeImage.getScaledInstance(box.SizeOf_width(), box.SizeOf_height(), Image.SCALE_SMOOTH);
        ImageIcon backgroundIcon = new ImageIcon(afterImage);

        MainFrame = new JFrame();
        MainFrame.setTitle("Game");
        MainFrame.setSize(box.SizeOf_width(), box.SizeOf_height());
        MainFrame.setResizable(false);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 카드레이아웃 --> 두 패널 바꾸는데 사용
        JPanel CardPanel = new JPanel(new CardLayout());
        MainFrame.add(CardPanel);
        CardLayout CL = (CardLayout) CardPanel.getLayout();

        // 패널1 (이름 입력칸, 확인)
        mainPanel1 = createPanel(backgroundIcon, 0, 0);
        mainPanel1.setLayout(new GridBagLayout());       // 레이아웃을 GridBagLayout으로 설정
        CardPanel.add(mainPanel1);                       // frame에 추가

        // 이름 입력 - 패널1
        JPanel nameArea = createPanel(new ImageIcon(getClass().getResource("/image/name.png")), 0, 0);
        nameArea.setPreferredSize(new Dimension(200, 60));

        JTextField name = new JTextField();
        name.setBorder(BorderFactory.createEmptyBorder());      // 테두리없애기
        name.setOpaque(false);                                  // 투명배경
        name.setFont(new Font("여기어때 잘난체 고딕 TTF", Font.PLAIN, 30));    // Font 설정

        nameArea.setLayout(new BorderLayout());
        nameArea.add(name, BorderLayout.CENTER);
        addButton(mainPanel1, nameArea, 0, 0, 230, 0, 0, 0);    // panel1에 nameArea 추가

        // 확인버튼 - 패널1 -> 이름이 입력되지 않으면 file 입력하는 부분에서 IOecexption뜨게되고, 이걸 try-catch로 잡아야됨
        JButton okayButton = createButton("/image/okaybutton.png", "/image/okaybutton2.png");
        addButton(mainPanel1, okayButton, 0, 1, 20, 0, 0, 0);
        okayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_name = name.getText();
                System.out.println(user_name);
                CL.next(CardPanel);
            }
        });

        // background image2
        Image beforeImage2 = new ImageIcon(getClass().getResource("/image/background2.png")).getImage();
        Image afterImage2 = beforeImage2.getScaledInstance(box.SizeOf_width(), box.SizeOf_height(), Image.SCALE_SMOOTH);
        ImageIcon backgroundIcon2 = new ImageIcon(afterImage2);

        // 패널2 (Start, Ranking)
        mainPanel2 = createPanel(backgroundIcon2, 0, 0);
        mainPanel2.setLayout(new GridBagLayout());       // 레이아웃을 GridBagLayout으로 설정
        CardPanel.add(mainPanel2);                                 // 메인패널을 frame에 추가

        // 시작버튼 - 패널2
        JButton startButton = createButton("/image/startbutton.png", "/image/startbutton2.png");
        addButton(mainPanel2, startButton, 0, 0, 250, 0, 0, 0);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.setVisible(false);
                DF.setvisible(true);
            }
        });

        // 랭킹버튼 - 패널2
        JButton rankButton = createButton("/image/rankingbutton.png", "/image/rankingbutton2.png");
        addButton(mainPanel2, rankButton, 0, 1, 20, 0, 0, 0);

        // back버튼 - 패널2
        JButton backButton = createButton("/image/backbutton.png", "/image/backbutton2.png");
        addButton(mainPanel2, backButton, 0, 2, 15, 0, 0, 0);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CL.next(CardPanel);
            }
        });


        MainFrame.setVisible(visible);
    }

    // 패널 제작 함수
    private JPanel createPanel(ImageIcon image, int x, int y) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(image.getImage(), x, y, this);
            }
        };
        return panel;
    }

    // 버튼 제작 함수
    private JButton createButton(String image1, String image2) {
        ImageIcon buttonIcon = new ImageIcon(getClass().getResource(image1));
        ImageIcon rolloverIcon = new ImageIcon(getClass().getResource(image2));
        JButton button = new JButton(buttonIcon);
        button.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        button.setRolloverIcon(rolloverIcon);   // 마우스를 댔을 때의 이미지설정
        return button;
    }

    // 버튼을 패널에 추가하는 함수
    private void addButton(Container panel, Component button, int gridx, int gridy, int top, int left, int bottom, int right) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;  // 행
        gbc.gridy = gridy;  // 열
        gbc.insets = new Insets(top, left, bottom, right);         // 여백 추가 -> 버튼 위치 조절하려고
        panel.add(button, gbc);
    }

    public String getUser_name() {
        return user_name;
    }

    public void setvisible(boolean n) {
        visible = n;
        MainFrame.setVisible(visible);
    }

}