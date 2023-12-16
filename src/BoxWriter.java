import java.awt.*;

public class BoxWriter {
    private Box box;

    // Constructor (생성자)
    public BoxWriter(Box b){
        box = b;
    }

    // setter
    public void paint(Graphics g) {     // 왼쪽에 흰색 없애기
        int xsize = box.SizeOf_width();
        int ysize = box.SizeOf_height();
        g.setColor(Color.black);                            // background color
        g.fillRect(0, 0, xsize, ysize);     // Rect
        g.setColor(Color.white);                            // ??
        g.drawRect(0, 0, xsize, ysize);     // Rect
    }
}