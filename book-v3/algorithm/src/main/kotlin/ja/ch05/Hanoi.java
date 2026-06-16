package ja.ch05;

public class Hanoi {
    static void move(int no, int x, int y) {
        if (no > 1) move(no - 1, x, 6 - x - y);
        System.out.println("원반["+no+"]을 " + x + "에서 " + y + "로 이동");
        if (no > 1) move(no - 1, 6 - x - y, y);

    }

    public static void main(String[] args) {
        move(3, 1, 3);
    }
}
