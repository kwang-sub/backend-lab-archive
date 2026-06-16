package book.chap02;

public class ReverseArray {
    static void swap(int[] a, int idx1, int idx2) {
        System.out.println("a[" + idx1 + "]과(와) a[" + idx2 + "]를 교환합니다.");
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static void reverse(int[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            swap(a, i, a.length - i - 1);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
        reverse(a);
    }
}
