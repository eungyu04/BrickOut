import java.awt.*;

public class PedalWriter {
    private Pedal pedal;
    private Box box;

    public PedalWriter(Pedal pedal, Box box) {
        this.pedal = pedal;
        this.box = box;
    }

    public void paint(Graphics g) {
        int size = box.SizeOf();
        g.setColor(Color.white);        // 색 지정
        g.fillRoundRect(pedal.getX_pos(), size - 100, pedal.get_width(), pedal.get_height(), 10, 10);   // pedal
    }

}
