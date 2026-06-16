package kr.co.billiejoe.place.exception;

public class SaveFileException extends RuntimeException {
	public SaveFileException() {
		super("업로드 과정 중에 오류 발생");
	}
}