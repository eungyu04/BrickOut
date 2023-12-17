import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {    // main()
        int box_width = 480;
        int box_height = 720;

        FrameController FC = new FrameController();

        Box box = new Box(box_width, box_height);
        Pedal Pd = new Pedal(150, 170, 20);
        Ball MB = new Ball(200, 400, 10, box, Pd, FC);

        BoxWriter BoW = new BoxWriter(box);
        PedalWriter PeW = new PedalWriter(Pd, box_height);
        BallWriter BaW = new BallWriter(MB, Color.WHITE);
        BrickWriter BrW = new BrickWriter(MB);

        AnimationWriter AW = new AnimationWriter(BoW, BaW, PeW, BrW, box_width, box_height);

        BounceController BC = new BounceController(MB, AW, Pd, FC);

        // Frame----------------------------------
        DifficultyFrame DF = new DifficultyFrame(FC, Pd, box, MB);
        MainFrame MF = new MainFrame(FC, box);
        RankingFrame RF = new RankingFrame(FC);
        FC.setClass(AW, DF, MF, RF);

        while (true) {
            BC.runAnimation();
        }
    }
}
