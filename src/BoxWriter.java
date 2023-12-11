import java.awt.*;

public class BoxWriter {
    private Box box;

    // Constructor (생성자)
    public BoxWriter(Box b){
        box = b;
    }

    // setter
    public void paint(Graphics g) {
        int size = box.SizeOf();
        g.setColor(Color.black);                            // background color
        g.fillRect(0, 0, size + 360, size);     // Rect
        g.setColor(Color.white);                            // ??
        g.drawRect(0, 0, size + 360, size);     // Rect
    }
}