package study.java.stream;

import java.util.Arrays;
import java.util.List;

public class MapAndReduceExample {
    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(
                new Student("1", 1),
                new Student("2", 2),
                new Student("3", 3)

        );

        double avg = studentList.stream()
//                중간처리 학생에게서 점수를 뽑아내는 과정
                .mapToInt(Student::getScore)
//                최종처리 중간에서 뽑은 점수를 최종에서 평균값을 구하는 과정
                .average()
                .getAsDouble();
        System.out.println("평균점수 " + avg);
    }
}
