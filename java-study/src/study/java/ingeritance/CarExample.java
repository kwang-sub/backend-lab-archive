package study.java.ingeritance;

public class CarExample {
    public static void main(String[] args) {
        Car car = new Car();
        
        for(int i=0; i<=5; i++){
            int problemLocation= car.run();
            
            switch (problemLocation){
                case 1 :
                    System.out.println("1타이어 교체");
                    car.fLTire = new HankookTire(14,"앞왼쪽");
                    break;
                case 2 :
                    System.out.println("2타이어 교체");
                    car.fRTire = new HankookTire(14,"앞오른쪽");
                    break;
                case 3 :
                    System.out.println("3타이어 교체");
                    car.bLTire = new HankookTire(14,"뒤왼쪽");
                    break;
                case 4 :
                    System.out.println("4타이어 교체");
                    car.bRTire = new HankookTire(14,"뒤오른쪽");
                    break;
            }
            System.out.println("================"+(i+1)+"회전==============");

        }
        
    }
}
