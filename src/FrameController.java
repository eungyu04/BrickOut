public class FrameController {
    private AnimationWriter gameFrame;
    private DifficultyFrame difficultFrame;
    private MainFrame mainFrame;
    private RankingFrame rankingFrame;

    // Frame정의
    public void setClass(AnimationWriter gF, DifficultyFrame dF, MainFrame mF, RankingFrame rF) {
        this.gameFrame = gF;
        this.difficultFrame = dF;
        this.mainFrame = mF;
        this.rankingFrame = rF;
    }

    public boolean getDifficulty() {
        if (difficultFrame.getUser_difficulty().equals("easy"))     // easy -> true
            return true;
        else                // hard -> false
            return false;
    }

    public String getUserName() {
        return mainFrame.getUser_name();
    }
    
    public void main2diff() {
        mainFrame.setvisible(false);
        difficultFrame.setvisible(true);
    }
    public void diff2main() {
        difficultFrame.setvisible(false);
        mainFrame.setvisible(true);
    }
    public void main2rank() {
        mainFrame.setvisible(false);
        rankingFrame.setvisible(true);
    }
    public void rank2main() {
        rankingFrame.setvisible(false); ;
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
    public void game2main() {
        gameFrame.setvisible(false); ;
        mainFrame.setvisible(true);
    }
    public void main2game() {
        mainFrame.setvisible(false); ;
        gameFrame.setvisible(true);
    }


}