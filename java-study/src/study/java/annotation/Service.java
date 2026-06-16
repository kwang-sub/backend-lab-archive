package study.java.annotation;

public class Service {
    @PrintAnnotation
    public void method1(){
        System.out.println("실행 내용 1");

    }

    @PrintAnnotation("*")
    public void method2(){
        System.out.println("2222222222");

    }

    @PrintAnnotation(value = "#", number = 20)
    public void method3(){
        System.out.println("333333333333333");
    }
}
