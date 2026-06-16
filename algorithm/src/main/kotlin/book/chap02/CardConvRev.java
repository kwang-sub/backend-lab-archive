package book.chap02;

public class CardConvRev {
    static int cardConvR(int x, int r, char[] d) {
        int digits = 0;
        String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        do {
            d[digits++] = dchar.charAt(x % r);
            x /= r;
        } while (x != 0);
        return digits;
    }

    public static void main(String[] args) {
        char[] cno = new char[32];
        int i = cardConvR(10, 2, cno);
        for (int j = i - 1; j >= 0; j--) {
            System.out.print(cno[j]);
        }
    }
}
