package edu.kh.io.model.service;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
//	File 클래스 : 파일 또는 폴더를 다룰 수 있는 자바 제공 클래스이고 객체 생성 시 선택 또는 생성할 파일명을 매개변수에 작성
//	boolean createNewFile() : 파일 생성 메소드로 파일 생성 성공시 true 실패시 false를 반환한다.그리고 IOException을 발생 시킬 가능성이 있다.
//	바이트 기반 스트림 : 1byte 단위로 데이터를 입/출력하는 스트림
//	FileInputStream : 파일의 내용을 1byte씩 입력 받는 스트림이고 객체 생성 시 FileNotFoundException 예외처리가 필요하다.
//	Stream 관련 객체들은 메모리를 소비하는 자원이자 연결된 프로그램으로의 통로로 극단적 상황에서는 데이터유실, 악성코드 등의 침입을 유발할 수 있다.
//	사용완료된 Stream은 finally를 이용하여 필수로 닫아줘야 한다.
//	int read() : 파일의 데이터를 1byte씩 순서대로 읽어온다. 내용이 없으면 -1반환
//	FileOutputStream : 1바이트 단위로 파일로 내용을 출력하는  프로그램 
//			매개변수에 작성된 파일명과 같은 파일이 없으면 새 파일을 생성하고 있으면 덮어쓰기
//			FileOutputStream("파일명",true) :
//			-true 미작성시 덮어쓰기
//			-true 작성시 이어쓰기
//
//	문자 기반 스트림 : 2byte 문자(유니코드) 단위로 데이터를 입/출력하는 문자 전용 스트림
//	BufferedReader : 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOService {
	public void example1() {
//	존재하지 않는 파일 test1.txt파일을 프로젝트 폴더에 생성
	try {
		
		File f1 = new File("text1.txt");
		if(f1.createNewFile()) {
			System.out.println("파일 생성 성공");
		}else {
			System.out.println(f1.getName() + "파일이 이미 존재합니다.");
		}
		File f2 = new File("C:/dev/test2.txt");
		if(f2.createNewFile()) {
			System.out.println(f2.getName() + "파일 생성 성공");
		}else {
			System.out.println(f2.getName() + "파일이 이미 존재합니다.");
		}
		File folder = new File("temp");
		if(!folder.exists()) {
			
			if(folder.mkdir()) {
				System.out.println(folder.getName() + " 폴더 생성 성공");
				
			}
			
			
		}
	
		File f3 = new File(folder.getName() + "/test3.txt");
		if(f3.createNewFile()) {
			System.out.println(f3.getName() + "파일 생성 성공");
		}else {
			System.out.println(f3.getName() + "파일이 이미 존재합니다.");
		}

		
	}catch(IOException e) {
		System.out.println("파일 생성 중 예외 발생");
		e.printStackTrace();
	}
	}
	public void example2() {
//		바이트 기반 스트림 : 1byte 단위로 데이터를 입/출력하는 스트림
//		파일로부터 입력된 내용을 프로그램이 읽음.
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("byte/byteInput.txt");
			int value = 0; 
			while((value = fis.read()) != -1) {
				System.out.print((char)value);
//				1바이트 단위로 읽어오기 때문에 2바이트인 한글은 깨진 살태로 읽어지고 콘솔창에 출력된다.
			}
		}catch (IOException e) {
//			IOException은 FileNotFoundException의 부모로써 다형성을 이용해 두 예외를 모두 잡을 수 있다.
			System.out.println("에외발생");
			e.printStackTrace();
			
		}finally {//예외가 발생하든 말든 반드시 실행되는 구문

			try {
				if(fis != null) {
					fis.close();
				}
			}catch(IOException e) {//스트림을 닫는 중 예외가 발생할 수 있다.
				e.printStackTrace();
			}
			
		}
	}
	public void example3() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("byte/byteOutput.txt");
			String content = "Hello World!!!@@@\n"
					+ "12345678901234567890\n"
					+ "최광섭\n";
			for(int i=0; i<10; i++) {
				for(int j=0; j<=i; j++) {
					content +="*";
				}
				content +="\n";
			}
			for(int i=0; i<content.length(); i++) {
				fos.write(content.charAt(i));
			}
			System.out.println("출력 완료");
		}catch(IOException e) {
			System.out.println("예외 발생");
			e.printStackTrace();
		}finally {
//			사용한 스트림 닫기 (자원 반환)
			try {
				if(fos != null) {
					
					fos.close();
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	public void example4() {
//		FileWriter : 프로그램 -> 파일로 출력하는 문자 기반 스트림
//		write() : byte 기반 스트림과 다르게 문자 기반 스트림은 문자 하나 또는 문장 단위로 출력이 가능하다.
		FileWriter fw = null;
		try {
			fw = new FileWriter("char/charOutput.txt"/*, true*/);
//			매개변수 뒤에 true 추가 시 이어 쓰기
			fw.write("자 이제 한 시간 남았습니다.\n");
			fw.write("졸지말고 화이팅~~\n");
			fw.write("afsdlmdsflm\n");
			fw.write("1516516\n");
		}catch (IOException e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(fw != null) {
					fw.close();
					
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void example5() {
//		FileReader : 파일 -> 프로그램으로 입력하는 문자 기반 스트림
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("char/charOutput.txt");
			int value = 0;
			while ((value =fr.read())!=-1) {
				System.out.print((char)value);
			}
			System.out.println("=====================");
			fr = new FileReader("char/charOutput.txt");
			br = new BufferedReader(fr);
			String temp = null;
			while((temp = br.readLine()) != null) {
				System.out.println(temp);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fr != null) {
					fr.close();
				}
				if(br != null) {
					
					br.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	public void example6() {
//		Buffered 보조 스트림을 이용한 어떤 형식의 파일이든 복사하기
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis =  new BufferedInputStream(new FileInputStream("img.png"));
			bos = new BufferedOutputStream(new FileOutputStream("kwnag.png"));
			int value = 0;
			while ((value =bis.read())!=-1) {
				bos.write(value);
			}
			System.out.println("복사완료");
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(bis != null)bis.close();
				if(bos != null)bos.close();
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
