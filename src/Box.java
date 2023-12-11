public class Box {
    private int box_size;

    // Constructor (생성자)
    public Box(int size) {
        box_size = size;
    }
    
    // 밖으로 나갔을 때 벽 튕김
    public boolean inHorizontalContact(int x_position) {    // x축 벽에 나갔는지 확인
        return x_position <= 0 || x_position >= box_size + 360;
    }
    public boolean inVerticalContact(int y_position) {      // y축 벽에 나갔는지 확인
        return y_position <= 0; // || y_position >= box_size;
    }
    public boolean outVerticalContact(int y_position) {      // y축 벽에 나갔는지 확인
        return y_position >= box_size;
    }

    // getter
    public int SizeOf() {
        return box_size;
    }
}
