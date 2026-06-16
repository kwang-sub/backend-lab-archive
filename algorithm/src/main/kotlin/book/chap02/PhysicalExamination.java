package book.chap02;

public class PhysicalExamination {

    static final int VMAN = 21;

    static class PhyscData {
        String name;
        int height;
        double vision;
    }

    static double aveHeight(PhyscData[] dat) {
        double sum = 0;

        for (PhyscData data : dat) {
            sum += data.height;
        }

        return sum / dat.length;
    }
}
