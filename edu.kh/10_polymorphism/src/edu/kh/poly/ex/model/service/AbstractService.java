package edu.kh.poly.ex.model.service;

import edu.kh.poly.ex.model.vo.Animal;
import edu.kh.poly.ex.model.vo.Chicken;
import edu.kh.poly.ex.model.vo.Dog;

public class AbstractService  {

	public void example1() {
		
//		Animal a1 = new Animal();
//		Animal는 추상클래스여서 객체를 만들 수 없다.
		
		Chicken c1 = new Chicken("조류", "잡식", "마당", 2);
		c1.move();
		c1.eat();
		c1.breath();
		
		
		Dog d1 = new Dog("강아지과", "잡식", "우리집");
		d1.move();
		d1.eat();
		d1.breath();
		
		Animal[] arr = new Animal[3];
		
		arr[0] = c1;
		
		arr[1] = d1;
		arr[2] = new Dog("강아지과", "쩝쩝 먹는다", "헥헥숨쉰다");
		System.out.println("========");
		for(Animal a : arr) {
			a.move();
			a.breath();
			a.eat();
		}
	}
}
