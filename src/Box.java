public class Box {
    private int box_width;
    private int box_height;

    // Constructor (생성자)
    public Box(int xsize, int ysize) {
        box_width = xsize;
        box_height = ysize;
    }

    // 밖으로 나갔을 때 벽 튕김
    public boolean inHorizontalContact(int x_position) {    // x축 벽에 나갔는지 확인
        return x_position <= 0 || x_position >= box_width;
    }
    public boolean inVerticalContact(int y_position) {      // y축 벽에 나갔는지 확인
        return y_position <= 0; // || y_position >= box_size;
    }
    public boolean outVerticalContact(int y_position) {      // y축 벽에 나갔는지 확인
        return y_position >= box_height;
    }   // 게임 종료

    // getter
    public int SizeOf_width() {
        return box_width;
    }
    public int SizeOf_height() {
        return box_height;
    }
}
