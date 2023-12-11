import javax.swing.*;
import java.io.*;

public class LoginFrame {
    private AnimationWriter AW;
    private MovingBall MB;
    private DifficultyFrame DF;
    private JFrame Login_frame;            // 여기서 정의해줘야 다른 함수에서도 사용 가능
    private boolean visible;
    private String name;    // 이름

    public LoginFrame() {


    }

    public void memberWrite() throws IOException {
        BufferedWriter file = new BufferedWriter(new FileWriter("member.txt"));
        file.write("eunkyu/ekek/12345");    // 이름/아이디/비밀번호
        file.close();
    }

    // 이거 두개는 다른 class로 옮겨야함
    public void hardWrite() throws IOException {
        BufferedWriter file = new BufferedWriter(new FileWriter("hard_member.txt"));
        file.write("eunkyu/11:11");    // 이름/시간
        file.close();
    }
    public void easyWrite() throws IOException {
        BufferedWriter file = new BufferedWriter(new FileWriter("easy_member.txt"));
        file.write("eunkyu/11:11");    // 이름/시간
        file.close();
    }

    public void setvisible(boolean n) {
        visible = n;
        Login_frame.setVisible(visible);
    }

}
