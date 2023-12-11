import java.awt.*;

public class AnimationManager {
    public static void main(String[] args) {    // main()
        Box box = new Box(720);
        Pedal Pd = new Pedal(470, 200, 20);
        MovingBall MB = new MovingBall(0, 0, 10, box, Pd);

        BoxWriter BoW = new BoxWriter(box);
        PedalWriter PeW = new PedalWriter(Pd, box);
        BallWriter BaW = new BallWriter(MB, Color.WHITE);

        AnimationWriter AW = new AnimationWriter(BoW, BaW, PeW, 720);

        BounceController BC = new BounceController(MB, AW, Pd);

        // Login


        // Difficulty
        DifficultyFrame DF = new DifficultyFrame(AW, MB);


        BC.runAnimation();
    }
}
