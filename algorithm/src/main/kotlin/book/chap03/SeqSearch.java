package book.chap03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SeqSearch {

    static int seqSearch(int[] a, int n, int key) {
//        int i = 0;
//        while (true) {
//            if (i == n) {
//                return -1;
//            }
//            if (a[i] == key) {
//                return i;
//            }
//            i++;
//        }
        for (int i = 0; i < n; i++) {
            if (a[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("요솟수 : ");
        int num = Integer.parseInt(br.readLine());
        int[] x = new int[num];

        for (int i = 0; i < num; i++) {
            System.out.println("x[" + i + "] : ");
            x[i] = Integer.parseInt(br.readLine());
        }

        System.out.println("검색할 값 : ");
        int key = Integer.parseInt(br.readLine());
        int idx = seqSearch(x, num, key);

        if (idx == -1) {
            System.out.println("그 값의 요소가 없습니다.");
        } else {
            System.out.println(key + "은(는) x[" + idx + "]에 있습니다.");
        }
    }
}
