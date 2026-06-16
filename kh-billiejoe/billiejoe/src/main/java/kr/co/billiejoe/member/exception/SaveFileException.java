package kr.co.billiejoe.member.exception;

public class SaveFileException extends RuntimeException {

	public SaveFileException() {
		super("업로드된 파일을 저장하는 과정에서 문제 발생");
	}

}
