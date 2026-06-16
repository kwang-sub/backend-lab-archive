package ja.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StringBinarySearch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] x = {"abstract", "assert", "catch"};

        System.out.println("원하는 키워드를 입력하세요.: ");
        String ky = br.readLine();
        int idx = Arrays.binarySearch(x, ky);
        if (idx < 0) {
            System.out.println("해당 키워드가 없습니다.");
        } else {
            System.out.println("해당 키워드는 x[" + idx + "]");
        }
    }
}
