import java.awt.*;

public class PedalWriter {
    private Pedal pedal;
    private int ysize;

    public PedalWriter(Pedal pedal, int y) {
        this.pedal = pedal;
        ysize = y;
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);            // 색 지정
        g.fillRoundRect(pedal.getX_pos(), ysize - 100, pedal.get_width(), pedal.get_height(), 10, 10);   // pedal
    }

}
