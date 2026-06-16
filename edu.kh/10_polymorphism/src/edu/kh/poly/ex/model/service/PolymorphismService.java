package edu.kh.poly.ex.model.service;

import edu.kh.poly.ex.model.vo.Car;
import edu.kh.poly.ex.model.vo.ModelX;
import edu.kh.poly.ex.model.vo.Porter;
import edu.kh.poly.ex.model.vo.Spark;

public class PolymorphismService {
	
//	업캐스팅 : 부모타입 참조 변수로 자식 객체 주소를 참조
//	
//	다운캐스팅 : 부모 타입의 참조 변수를 자식 타입으로 형변환하여 자식 객체를 온전히 참조함
	public void example1() {
	
		Car c = new Car();
//		부모 타입 참조변수  = 부모 객체
//		부모 타입 참조 변수로 부모 객체의 메소드를 접근할 수 있다.
		c.setEngine("엔진있음");
		c.setHandle("보통핸들");
		c.setWheel(4);
		
		Porter p = new Porter();
//		자식 타입 참조변수 = 차식 객체
		p.setEngine("경우엔진");
		p.setHandle("크고 동그란 핸들");
		p.setWheel(8);
		p.setWeight(4.5);
		p.setBay("큰 짐칸");
		
//		다형성 적용 
//		부모 타입 참조변수 = 자식 타입 객체
		Car c2 = new Porter();
//		부모 타입의 참조 변수는 자식 객체를 참조하고 있어도 상속 받은 부모 부분만 참조 가능하다.
		c2.setEngine("경유엔진");
		c2.setHandle("크고 동그란 핸들");
		c2.setWheel(8);
		
		System.out.println(c2.getEngine());
		System.out.println(c2.getHandle());
		System.out.println(c2.getWheel());
		System.out.println(c2);
		
		Car c3 = new Spark();
		Car c4 = new ModelX();
	}
	public void example2() {
		Car c =  new Spark("휘발유엔진", 4, "작은 핸들", "검정");
		
		Car c3 =  new Porter("경유엔진", 12, "엄청 큰 핸들", 11, "짱큼");
		
		System.out.println(c.getEngine());
		System.out.println(c.getWheel());
		System.out.println(c.getHandle());
		
//		System.out.println(c.getColor());
//		색상은 자식 멤버이므로 부모 참조 변수로는 사용불가
		
		System.out.println(((Spark)c).getColor());
//		Car 타입 참조 변수 c3를 자식인 Porter 타입 참조 변수로 다운 캐스팅하여 c3에 저장된 주소를 Poter에
		Porter p = (Porter)c3;
		System.out.println("====p====");
		System.out.println(p.getEngine());
		System.out.println(p.getHandle());
		System.out.println(p.getWheel());
		System.out.println(p.getWeight());
		System.out.println(p.getBay());
		
	}
	public void example3() {
		
//		객체배열과 다형성
//		배열 : 같은 자료형을 하나의 묶음으로 다루는 것
//		객체 배열 : 객체 참조 변수 여러 개를 하나의 묶음으로 다루는 것
//		Porter[] porters = new Porter[5];
//		Spark[] sparks = new Spark[10];
		
//		자동차 관리 프로그램
		Car[] carArr = new Car[5];
//		Car 참조 변수 5칸 짜리 배열을 Heap에 생성해서 해당 주소를 Car배열 참조변수 carArr에 대입하여 참조하게함.
		
		carArr[0] = new Porter("좋은엔진", 4, "보통핸들", 1, "작음");
		carArr[1] = new Spark("작은엔진", 5, "작은핸들", "민트색");
		carArr[2] = new ModelX("큰엔진", 7, "큰핸들", "이온전지");
		carArr[3] = new Car("모르는엔진", 5, "모르는핸들");
		carArr[4] = new Spark("작은엔진", 5, "작은핸들", "흰색");
		
//		carArr 각 배열 요소가 참조하는 객체 정보 모두 출력
//		System.out.println(((Porter)carArr[0]).toString());
//		carArr[]은 car 참조 변수이고 참조하고 있는 객체는 Porter이다
//		지금 원하는 것 : Porter에 오버라이딩된 toString() 호출
		System.out.println(((Spark)carArr[1]).toString());
		System.out.println(((ModelX)carArr[2]).toString());
		System.out.println(carArr[3].toString());
		System.out.println(carArr[4].toString());
		carArr[0].toString();
//		==정적바인딩 : 프로그램 실행 전 컴파일 단계에서 메소드와 메소드 호출부를 연결
//		carArr[0]은 Car 참조변수 이므로 Car클래스에 toString() 메소드를 호출한다.
//		Car클래스의 toString() 메소드와carArr[0].toString()이 연결됨.
		
//		==동적바인딩 : 프로그램 실행 시 정적 바인딩된 메소드를 실행하는 것이 아닌 참조하는 객체에 오버라이딩된 메소드와 연결하여 실행하는 것이다.
//		실제 코드 실행 시
//		carArr[0].toString() 구문이 Car 클래스의 toString() 아니라 오버라이딩 된 Porter 클래스의 toString()을 호출하게 됨.
		System.out.println("==============");
		for(int i = 0;  i <carArr.length; i++) {
			System.out.println(carArr[i].toString());
		}
		System.out.println("=============");
		for(Car car:carArr) {
			System.out.println(car);
		}
		System.out.println("=============");
		for(Car car:carArr) System.out.println(car);
	
		
	}
//	매개변수에 다형성 적용
//	매개변수의 타입을 달리하는 메소드를 작성할 필요가 없다는 이점이 있다.(코드 길이 감소, 재사용성)
	public void example4() {
		ModelX m1 = new ModelX("전기", 6, "간지좔좔", "수소전지");
		Porter p1 = new Porter("경유", 20, "큰", 20, "큰");
		Spark s1 = new Spark("작은", 4, "작은", "초록");
		Car c1 = new Car("경유 6기통", 4, "보통");
		printCar(m1);
		printCar(p1);
		printCar(s1);
		printCar(c1);
		
//		Car 객체 타입의 주소를 전달하거나 Car와 상속 관계에 있는 객체 타입의 주소를 전달해야 한다.(다형성 (업캐스팅))
	}
	public void printCar(Car car) {
//		(Car car) : Car 타입 객체 주소를 전달 받는 매개변수 ,Car 타입 참조 변수 , Porter, Spark, ModelX의 부모타입 참조 변수
		
//		instanceof 연산자
//		1. 참조변수가 참조하는 객체가 특정 자료형 객체가 맞는가
//		2. 참조변수가 참조하는 객체가 특정 자료형을 상속 받았는가
//		   -> true == 특정 자료형으로  업캐스팅, 다운캐스팅이 가능한 상태
		if(car instanceof Porter) { 
			Porter p = (Porter)car;
			System.out.printf("%s엔진을 사용하는 %.1f톤 포터는 %s짐칸과 %d개의 바퀴, %s핸들을 사용한다.\n",
	                  p.getEngine(), p.getWeight(), p.getBay(), p.getWheel(), p.getHandle());
		}else if(car instanceof Spark) {
			Spark s = (Spark) car;
			System.out.printf("%s색 스파크는 %s엔진을 사용해서 연비가 좋다.\n", s.getColor(), s.getEngine());
		}else if(car instanceof ModelX) {
			ModelX m = (ModelX)car;
			System.out.printf("테슬라에서 만든 ModelX는 %s배터리와, %s엔진을 사용한다. \n",m.getBattery(),m.getEngine());
		}else if (car instanceof Car) {
			System.out.printf("차종은 모르지만 %s엔진, 바퀴%d개, %s핸들이 있다.\n", car.getEngine(), car.getWheel(), car.getHandle());
		}
	}
	public Car searchCar(Car c) {
//		Car[] 객체배열 3칸을 선언해서 각 인덱스에 Porter, Car, Spark 객체를 초기화
//		전달받은 c와 타입이 같은 객체가 Car[] 객체 배열에 존재하면 해당 객체를 반환 없으면 null을 반환
		Car[] carArr = new Car[3];
		carArr[0] = new Porter("경유", 8, "중", 5, "중");
		carArr[1] = new Car("좋은", 4, "작은");
		carArr[2] = new Spark("작은", 4, "작은", "작은");
		if(c instanceof Porter) {
			
			return carArr[0];
		
		}else if (c instanceof Spark) {
			
			return carArr[2];
		
		}else if(c instanceof ModelX) {
			
			return null;	
		
		}else if(c instanceof Car){
			return carArr[1];
		}
//		전달 받은 c가 아무것도 참조하지 않는 null일 경우 
		return null;
		
	}
	public void example5() {
		Porter p1 = new Porter();
		System.out.println(searchCar(p1));
		System.out.println(searchCar(new Spark()));
		System.out.println(searchCar(new Car()));
		System.out.println(searchCar(null));
		
		Car c1 = searchCar(new Spark());
		System.out.println("c1 : " +c1);
	}
	
}
