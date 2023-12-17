import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RankingFrame {
    private FrameController FC;

    private JFrame RankingFrame;
    private JPanel RankingPanel1;
    private JPanel RankingPanel2;

    private List<String> easyMember;
    private List<String> hardMember;

    private boolean visible = true;

    public RankingFrame(FrameController FC) {
        this.FC = FC;

        RankingFrame = new JFrame();
        RankingFrame.setTitle("Ranking");
        RankingFrame.setSize(1080,720);
        RankingFrame.setResizable(false);
        RankingFrame.setLocationRelativeTo(null);
        RankingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RankingFrame.setLayout(new GridLayout(1,2));

        RankingPanel1 = new JPanel();
        RankingFrame.add(RankingPanel1);

        RankingPanel2 = new JPanel();
        RankingFrame.add(RankingPanel2);

        try {
            easyMember = readUser("/easy_member.txt");
            System.out.println("파일입력성공");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        addRank(RankingPanel1, easyMember, "EASY");
    }

    private void addRank(JPanel panel, List<String> rankingList, String title ) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(titleLabel);

        JTextArea rankingTextArea = new JTextArea(20, 20);
        rankingTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(rankingTextArea);

        panel.add(scrollPane);
        for (String entry : rankingList) {
            rankingTextArea.append(entry + "\n");
        }
    }

    private List<String> readUser(String filePath) throws Exception {
        List<String> users = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String user;
            
            while ((user = reader.readLine()) != null) {    // 줄 기준으로 읽어옴
                System.out.println(user);
                users.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void setvisible(boolean n) {
        visible = n;
        RankingFrame.setVisible(visible);
    }

}
