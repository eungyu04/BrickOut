import java.awt.*;

public class Main {
    public static void main(String[] args) {    // main()
        int box_width = 480;
        int box_height = 720;

        FrameController FC = new FrameController();

        Box box = new Box(box_width, box_height);
        Pedal Pd = new Pedal(150, 170, 20);
        Ball MB = new Ball(0, 0, 10, box, Pd);

        BoxWriter BoW = new BoxWriter(box);
        PedalWriter PeW = new PedalWriter(Pd, box_height);
        BallWriter BaW = new BallWriter(MB, Color.WHITE);
        BrickWriter BrW = new BrickWriter();

        AnimationWriter AW = new AnimationWriter(BoW, BaW, PeW, BrW, box_width, box_height);

        BounceController BC = new BounceController(MB, AW, Pd);

        // Frame----------------------------------
        DifficultyFrame DF = new DifficultyFrame(FC, Pd, box, MB);
        MainFrame MF = new MainFrame(FC, box);
        RankingFrame RF = new RankingFrame(FC);
        FC.setClass(AW, DF, MF, RF);

        BC.runAnimation();
    }
}
