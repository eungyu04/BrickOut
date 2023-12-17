import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame {
    private FrameController FC;

    private JFrame MainFrame;
    private JPanel mainPanel1;
    private JPanel mainPanel2;
    private boolean visible = true;
    private String user_name;

    public MainFrame(FrameController FC, Box box) {
        this.FC = FC;

        MainFrame = new JFrame();
        MainFrame.setTitle("Main");
        MainFrame.setSize(box.SizeOf_width(), box.SizeOf_height());
        MainFrame.setResizable(false);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 카드레이아웃 --> 두 패널 바꾸는데 사용
        JPanel CardPanel = new JPanel(new CardLayout());
        MainFrame.add(CardPanel);
        CardLayout CL = (CardLayout) CardPanel.getLayout();

        // 패널1 (이름 입력칸, 확인)
        mainPanel1 = createPanel(new ImageIcon(getClass().getResource("/image/background1.png")), 0, 0);
        mainPanel1.setLayout(new GridBagLayout());       // 레이아웃을 GridBagLayout으로 설정
        CardPanel.add(mainPanel1);                       // frame에 추가

        // 이름 입력 - 패널1
        JPanel nameArea = createPanel(new ImageIcon(getClass().getResource("/image/name.png")), 0, 0);
        nameArea.setPreferredSize(new Dimension(200, 60));
        nameArea.setLayout(new BorderLayout());

        JTextField name = new JTextField();
        name.setBorder(BorderFactory.createEmptyBorder());      // 테두리없애기
        name.setOpaque(false);                                  // 투명배경
        name.setFont(new Font("여기어때 잘난체 고딕 TTF", Font.PLAIN, 30));    // Font 설정
        ((AbstractDocument) name.getDocument()).setDocumentFilter(new NoSpaceFilter());     // 공백 입력을 못하게

        nameArea.add(name, BorderLayout.CENTER);
        addButton(mainPanel1, nameArea, 0, 0, 230, 0, 0, 0);    // panel1에 nameArea 추가

        // 확인버튼 - 패널1
        JButton okayButton = createButton("/image/okaybutton.png", "/image/okaybutton2.png");
        addButton(mainPanel1, okayButton, 0, 1, 20, 0, 0, 0);
        okayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_name = name.getText();
                System.out.println(user_name);
                if(checkName(user_name)) CL.next(CardPanel);
            }
        });

        // 패널2 (Start, Ranking)
        mainPanel2 = createPanel(new ImageIcon(getClass().getResource("/image/background2.png")), 0, 0);
        mainPanel2.setLayout(new GridBagLayout());       // 레이아웃을 GridBagLayout으로 설정
        CardPanel.add(mainPanel2);                                 // 메인패널을 frame에 추가

        // 시작버튼 - 패널2
        JButton startButton = createButton("/image/startbutton.png", "/image/startbutton2.png");
        addButton(mainPanel2, startButton, 0, 0, 250, 0, 0, 0);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FC.main2diff();
            }
        });

        // 랭킹버튼 - 패널2
        JButton rankButton = createButton("/image/rankingbutton.png", "/image/rankingbutton2.png");
        addButton(mainPanel2, rankButton, 0, 1, 20, 0, 0, 0);
        rankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FC.main2rank();
            }
        });

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

    // 이름 체크 함수
    private boolean checkName(String user_name) {
        try {
            if (user_name.charAt(0) == ' ') {
                return false;
            } else {
                return true;
            }
        } catch (Exception e ) {
            return false;
        }
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

// 공백입력 못하게 하기위한 클래스
class NoSpaceFilter extends DocumentFilter {
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if (containsSpace(text)) {
            // 공백이 포함된 경우 입력을 허용하지 않음
            return;
        }
        super.replace(fb, offset, length, text, attrs);
    }

    private boolean containsSpace(String text) {
        return text != null && text.contains(" ");
    }
}