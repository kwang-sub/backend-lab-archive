package chap01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JudgeSign {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("정수를 입력하세요.");
        int n = Integer.parseInt(br.readLine());
        if (n > 0) System.out.println("이 수는 양수입니다.");
        else if (n < 0) System.out.println("이 수는 음수입니다.");
        else System.out.println("이 수는 0입니다.");
    }
}
