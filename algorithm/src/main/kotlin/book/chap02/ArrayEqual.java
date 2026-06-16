package book.chap02;

public class ArrayEqual {
    static boolean equals(int[] a, int[] b) {
        if (a.length != b.length) return false;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 23, 4};
        int[] b = new int[]{1, 23, 4};

        System.out.println(equals(a, b));
    }
}
