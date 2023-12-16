public class FrameController {
    public AnimationWriter gameFrame;
    private DifficultyFrame difficultFrame;
    private MainFrame mainFrame;

    // 생성자 역할
    public void setClass(AnimationWriter gF, DifficultyFrame dF, MainFrame mF) {
        this.gameFrame = gF;
        this.difficultFrame = dF;
        this.mainFrame = mF;
    }

    //
    public void main2diff() {
        mainFrame.setvisible(false);
        difficultFrame.setvisible(true);
    }
    public void diff2main() {
        difficultFrame.setvisible(false);
        mainFrame.setvisible(true);
    }
    public void diff2game() {
        difficultFrame.setvisible(false);
        gameFrame.setvisible(true);
    }
    public void game2diff() {
        gameFrame.setvisible(false); ;
        difficultFrame.setvisible(true);
    }

}