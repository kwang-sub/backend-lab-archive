package edu.kh.eh.model.vo;

import java.io.IOException;

public class Parent {
	
	public void ex1() throws RuntimeException{
		
	}
	public void ex2() throws RuntimeException, IOException{
//		RuntimeException, IOException 발생시 호출한 곳으로 예외를 던지는 처리를함
	}

}
