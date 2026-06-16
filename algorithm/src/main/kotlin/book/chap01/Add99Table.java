package book.chap01;

public class Add99Table {
    public static void main(String[] args) {
        System.out.println("-------- 곱셈표 ----------");
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (i == 1 && j ==1) {
                    System.out.printf("    |");
                    for (int z = 1; z < 10; z++) {
                        System.out.printf("%3d", z);
                    }
                    System.out.println();
                    System.out.println("---+-------------------------------");
                }
                if (j == 1) {
                    System.out.printf("%3d |", i);
                }
                System.out.printf("%3d", i + j);
            }
            System.out.println();
        }
    }
}
