package study.java.stream;

import java.util.stream.IntStream;

public class FromIntRangeExample {
    public static int sum;

    public static void main(String[] args) {
// rangeClosed()메소드는 첫번째매개값부터 두번째값까지 순차적으로 제공하는 Int Stream을 반환한다.range() 메소드는 두번째 인자값을 포함하지 않는다
        IntStream stream = IntStream.rangeClosed(1, 100);
        stream.forEach(a -> sum += a);
        System.out.println("총합 : " + sum);

    }
}
