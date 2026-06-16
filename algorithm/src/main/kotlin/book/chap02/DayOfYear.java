package book.chap02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DayOfYear {
    static int[][] mdays = {
            {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
            {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
    };

    static int isLeaf(int year) {
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 1 : 0;
    }

    static int dayOfYear(int y, int m, int d) {
//        int days = d;
//        for (int i = 1; i < m; i++) {
//            days += mdays[isLeaf(y)][i - 1];
//        }
//        return days;

        m--;
        while (m > 0) {
            d += mdays[isLeaf(y)][m-- - 1];
        }
        return d;
    }

    static int leftDayOfYear(int y, int m, int d) {
        int days = mdays[isLeaf(y)][m - 1] - d;
        m++;
        while (m < 13) {
            days += mdays[isLeaf(y)][m++ - 1];
        }
        return days;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int retry;

        System.out.println("그 해 남은 일수를 구합니다.");

        do {
            System.out.println("년 : ");
            int year = Integer.parseInt(br.readLine());
            System.out.println("월 : ");
            int month = Integer.parseInt(br.readLine());
            System.out.println("일 : ");
            int day = Integer.parseInt(br.readLine());

            System.out.printf("그 해 %d일째입니다. \n", leftDayOfYear(year, month, day));
            System.out.println("한 번 더 할까요? (1.예 / 0.아니오)");
            retry = Integer.parseInt(br.readLine());
        } while (retry == 1);
    }
}
