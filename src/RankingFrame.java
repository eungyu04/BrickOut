import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class RankingFrame {
    private FrameController FC;

    private JFrame RankingFrame;
    private JPanel mainPanel;
    private JPanel RankingPanel1;
    private JPanel RankingPanel2;
    private JButton backButton;

    private List<String> easyMember;    // "name/score/time"
    private List<String> hardMember;    // "name/score/time"

    private boolean visible = true;

    public RankingFrame(FrameController FC) {
        this.FC = FC;

        RankingFrame = new JFrame();
        RankingFrame.setTitle("Ranking");
        RankingFrame.setSize(1080,720);
        RankingFrame.setResizable(false);
        RankingFrame.setLocationRelativeTo(null);
        RankingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.black);
        RankingFrame.add(mainPanel);

        // 두 개의 panel (난이도별로)
        RankingPanel1 = new JPanel(new BorderLayout());   // easyPanel
        RankingPanel1.setBackground(Color.black);
        mainPanel.add(RankingPanel1,BorderLayout.WEST);

        RankingPanel2 = new JPanel(new BorderLayout());   // hardPanel
        RankingPanel2.setBackground(Color.black);
        mainPanel.add(RankingPanel2,BorderLayout.EAST);

        // back button (main패널의 south에 검정색 패널을 하나 더 넣고, 그 가운데에 button을 추가)
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.black);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        backButton = createButton("/image/backbutton.png", "/image/backbutton2.png");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FC.rank2main();
            }
        });
        southPanel.add(backButton);


        // 여기서부터 파일 불러옴
        try {
            easyMember = readUser("src/member/easy_member.txt");
            hardMember = readUser("src/member/hard_member.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        sort(easyMember);   // 정렬
        sort(hardMember);

        addRank(RankingPanel1, easyMember, "EASY");
        addRank(RankingPanel2, hardMember, "HARD");

    }

    // USER 파일 불러오기
    private List<String> readUser(String filePath) throws Exception {
        List<String> users = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String user;
            
            while ((user = reader.readLine()) != null) {    // 줄 기준으로 읽어옴
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    // 랭킹에 맞게 정렬
    private void sort(List<String> USERLIST) {
        int[] scores = new int[USERLIST.size()];
        int[] times = new int[USERLIST.size()];
        int temp;

        // 벽돌부신개수, 시간을 새로운 배열에 저장 (비교하려고)
        for (int i = 0; i<USERLIST.size(); i++) {
            StringTokenizer t = new StringTokenizer(USERLIST.get(i), "/");
            t.nextToken();  // 이름
            try {
                scores[i] = Integer.parseInt(t.nextToken());
                times[i] = Integer.parseInt(t.nextToken());
            } catch (NumberFormatException e) {}
        }

        // 점수에 따라 정렬 (람다식을 사용해도 되긴 하는데 그냥)
        for (int i = 0; i<USERLIST.size(); i++) {
            for (int j = i + 1; j < USERLIST.size(); j++) {
                if (scores[i] < scores[j]) {
                    temp = scores[i];
                    scores[i] = scores[j];
                    scores[j] = temp;
                    Collections.swap(USERLIST, i, j);   // 원본 리스트도 바뀜
                }
            }
        }

        // 점수가 같을 때 시간이 짧은 사람이 되게
        for (int i = 0; i<USERLIST.size(); i++) {
            for (int j = i + 1; j < USERLIST.size(); j++) {
                if (scores[i] == scores[j] && times[i] < times[j]) {
                    temp = times[i];
                    times[i] = times[j];
                    times[j] = temp;
                    Collections.swap(USERLIST, j, i);   // 원본 리스트도 바뀜
                }
            }
        }
    }

    public void update() {

    }
    
    // GUI에 추가
    private void addRank(JPanel panel, List<String> rankingList, String title ) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("여기어때 잘난체 고딕 TTF", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.white);
        titleLabel.setBorder(new EmptyBorder(10,0,5,0));    // 여백
        panel.add(titleLabel, BorderLayout.NORTH);

        JTextArea rankingTextArea = new JTextArea(20, 20);
        rankingTextArea.setEditable(false);                                 // 편집X
        rankingTextArea.setFont(new Font("여기어때 잘난체 고딕 TTF", Font.PLAIN, 20));
        rankingTextArea.setBackground(Color.black);
        rankingTextArea.setForeground(Color.white);
//        rankingTextArea.setOpaque(false);
        rankingTextArea.setBorder(new EmptyBorder(5,3,0,0));    // 여백

        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(rankingTextArea);
        customizeScrollbar(scrollPane.getVerticalScrollBar());

        panel.add(scrollPane);
        
        // 랭킹대로 TextArea에 추가
        int grade = 0;
        for (String entry : rankingList) {
            rankingTextArea.append(grade+1 + "등 : " + rankingList.get(grade).split("/")[0] +
                    "\t" + rankingList.get(grade).split("/")[1] +
                    "개 (" + rankingList.get(grade).split("/")[2] + "초)\n");
            grade++;
        }
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
    
    // 마우스 휠 커스터마이징 --> 제출할 때는 지우고
    private static void customizeScrollbar(JScrollBar scrollBar) {
        scrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.black; // 스크롤바 색상 변경
                this.thumbDarkShadowColor = Color.black; // 스크롤바 그라데이션 시작 색상 변경
                this.thumbHighlightColor = Color.white; // 스크롤바 그라데이션 끝 색상 변경
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                Dimension zeroDim = new Dimension(0, 0);
                button.setPreferredSize(zeroDim);
                button.setMinimumSize(zeroDim);
                button.setMaximumSize(zeroDim);
                return button;
            }
        });
    }
    
    public void setvisible(boolean n) {
        visible = n;
        RankingFrame.setVisible(visible);
    }

}