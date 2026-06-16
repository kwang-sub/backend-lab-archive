package study.java.ingeritance;

public class Car {
    Tire fLTire = new Tire(6, "앞왼쪽");
    Tire fRTire = new Tire(2, "앞오른쪽");
    Tire bLTire = new Tire(3, "뒷왼쪽");
    Tire bRTire = new Tire(4, "뒷오른쪽");

    int run(){
        System.out.println("[자동차 달리기]");
        if (fLTire.roll()==false){stop(); return 1;}
        if (fRTire.roll()==false){stop(); return 2;}
        if (bLTire.roll()==false){stop(); return 3;}
        if (bRTire.roll()==false){stop(); return 4;}
        return 0;
    }
    
    void stop(){
        System.out.println("자동차 정지");
    }
}
