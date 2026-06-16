package edu.kh.eh.model.exception;

public class SonjeolException extends Exception{
	public SonjeolException() {
		super("3번 손절 되었습니다");
	}
	public SonjeolException(String msg) {
		super(msg);
	}
}
