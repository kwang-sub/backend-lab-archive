package ja.ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IntStackTester {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        IntStack s = new IntStack(64);

        while (true) {
            System.out.println("현재 데이터 수 : " + s.size() + " / " + s.capacity());
            System.out.println("(1)푸시 (2)팝 (3)피크 (4)덤프 (0)종료");

            int menu = Integer.parseInt(br.readLine());
            if (menu == 0) break;


            int x;
            switch (menu) {
                case 1:
                    System.out.println("데이터: ");
                    try {

                        x = Integer.parseInt(br.readLine());
                        s.push(x);
                    } catch (IntStack.OverflowIntStackException e) {
                        System.out.println("스택이 가득 찼습니다.");
                    }
                    break;

                case 2:
                    try {
                        x = s.pop();
                        System.out.println("팝한 데이터는 " + x + "입니다.");
                    } catch (IntStack.EmptyIntStackException e) {
                        System.out.println("스택이 비어 있습니다.");
                    }
                    break;
                case 3:
                    try {

                        x = s.peek();
                        System.out.println("피크한 데이터는 " + s + "입니다.");
                    } catch (IntStack.EmptyIntStackException e) {
                        System.out.println("스택이 비어 있습니다.");
                    }

                    break;
                case 4:
                    s.dump();
                    break;

            }
        }
    }
}
