package book.chap01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Digits {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int no;

        System.out.println("2자리의 정수를 입력하세요.");

        do {
            System.out.println("입력 : ");
            no = Integer.parseInt(br.readLine());
        } while (!(no > 9 && no < 100));

        System.out.println("변수 no의 값은 " + no + "가(이) 되었습니다.");
    }
}
