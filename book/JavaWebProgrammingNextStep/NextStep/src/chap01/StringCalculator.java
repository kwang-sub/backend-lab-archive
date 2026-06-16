package chap01;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringCalculator {
    public int add(String text) throws RuntimeException {
        if (isBlank(text)) {
            return 0;
        }
        return sum(split(text));
    }

    private StringTokenizer split(String text) {
        boolean customCheck = text.startsWith("//");

        if (customCheck) {
            String a = String.valueOf(text.charAt(2));
            text = text.substring(text.indexOf('\n') + 1);

            return new StringTokenizer(text, a);
        }

        return new StringTokenizer(text, ",|:");
    }

    private boolean isBlank(String text) {
        return text == null || text.isEmpty();
    }

    private int sum(StringTokenizer st) {
        int sum = 0;
        List<Integer> numbers = toInt(st);
        for (int n : numbers) {
            sum += n;
        }
        return sum;
    }

    private List<Integer> toInt(StringTokenizer st) {
        List<Integer> numbers = new ArrayList<>();
        while (st.hasMoreTokens()) {
            int n = Integer.parseInt(st.nextToken());
            if (n < 0) {
                throw new RuntimeException();
            }
            numbers.add(n);
        }
        return numbers;
    }
}
