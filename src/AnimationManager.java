import java.awt.*;

public class AnimationManager {
    public static void main(String[] args) {    // main()
        int box_width = 480;
        int box_height = 720;

        Box box = new Box(box_width, box_height);
        Pedal Pd = new Pedal(150, 170, 20);
        MovingBall MB = new MovingBall(0, 0, 10, box, Pd);

        BoxWriter BoW = new BoxWriter(box);
        PedalWriter PeW = new PedalWriter(Pd, box);
        BallWriter BaW = new BallWriter(MB, Color.WHITE);

        AnimationWriter AW = new AnimationWriter(BoW, BaW, PeW, box_width, box_height);

        BounceController BC = new BounceController(MB, AW, Pd);

        // Difficulty
        DifficultyFrame DF = new DifficultyFrame(AW, MB, box);

        // Main
        MainFrame MF = new MainFrame(DF, box);

        BC.runAnimation();
    }
}
