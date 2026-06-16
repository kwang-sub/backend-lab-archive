package edu.kh.eh.model.vo;

import java.io.IOException;

public class Child extends Parent {
	@Override
	public void ex1() throws ArithmeticException{
//		ArithmeticException은 RuntimeException의 자식
	}
	
	@Override
	public void ex2() throws RuntimeException, IOException {
//		자식이 오버라이딩 시  예리를 처리할 수 있거나 재정의한 코드가 예외를 발생시키지 않으면 throws에 작성된 예외를 개수를 적게 또는 작성하지 않을 수 있다.
		
	}
}
