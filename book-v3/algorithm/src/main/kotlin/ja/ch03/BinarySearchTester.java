package ja.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BinarySearchTester {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("요솟수: ");
        int num = Integer.parseInt(br.readLine());
        int[] x = new int[num];

        System.out.println("오름차순으로 입력하세요.");

        System.out.print("x[0]: ");
        x[0] = Integer.parseInt(br.readLine());

        for (int i = 1; i < num; i++) {
            do {
                System.out.print("x[" + i + "]:");
                x[i] = Integer.parseInt(br.readLine());
            } while (x[i] < x[i - 1]);
        }

        System.out.println("검색할 값: ");
        int key = Integer.parseInt(br.readLine());

        int idx = Arrays.binarySearch(x, key);

        if (idx < 0) {
            System.out.println("그 값의 요소가 없습니다. " + idx);
        } else {
            System.out.println(key + "은(는) x[" + idx + "]에 있습니다.");
        }
    }
}
