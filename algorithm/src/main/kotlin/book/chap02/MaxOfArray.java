package book.chap02;

public class MaxOfArray {
    static int maxOf(int[] a) {
        int max = a[0];

        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] height = new int[]{167, 171, 154, 192, 183};
        int result = maxOf(height);
        System.out.println("최댓값은 " + result + "입니다.");
    }
}
