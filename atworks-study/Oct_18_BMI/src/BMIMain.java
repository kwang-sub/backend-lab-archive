import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BMIMain {

	// 사용자에게 입력을 받을거고
	// 이름 입력받고
	// 성별 입력받고
	// 키(cm)로 입력받고
	// 몸무게 입력받고

	// 입력받은거 가지고 bmi계산하는 함수작성
	// bmi가지고 판정하는 함수 작성
	// 위에한거 다 가지고 프린트하는 함수 작성
	// 출력 예

	// -----------BMI 계산 결과 ----------------
	// 이름 : ??
	// 성별 : ??
	// 키 : ??
	// 몸무게 ??
	// BMI지수 : ??
	// 판정결과 : ??
	// ---------------------------------------

	public static void main(String[] args) {

		String name = getUserName();
		String gender = getUserGender();
		double height = getUserHeight();
		double weight = getUserWeight();
		double BMI = calculate(height, weight);
		String judgment = judgment(BMI);
		sout(name, gender, height, weight, BMI, judgment);
	}

	public static String getUserName() {
		// 이름에는 숫자가 들어올 수 없음
		// 영어, 한글
		// 최광섭5
		Scanner sc = new Scanner(System.in);
		System.out.println("이름 입력하세요. ");
		String name = sc.next();
		String regExp3 = ".*[0-9].*";
		if(name.matches(regExp3)){
			System.out.println("숫자가 들어가는 사람이 어딨나요?");
			return getUserName();
		}else {
			return name;
		}
	}

	public static String getUserGender() {

		Scanner sc = new Scanner(System.in);
		System.out.println("성별 입력하세요. (m/w)");
		String gender = sc.next();
		String reg = ".*[0-9].*";
		if(gender.matches(reg)){
			System.out.println("숫자가 들어가는 사람이 어딨나요?");
			return getUserGender();
		}else {
			return gender;
		}
	}

	
	public static double getUserHeight() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("키 입력하세요. ");
			double height = sc.nextDouble();
			return height;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("숫자만입력해라");
			return getUserHeight();
		}
	}

	public static double getUserWeight() {
		
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("몸무게 입력하세요. ");
			double weight = sc.nextDouble();
			return weight;
			
		} catch (Exception e) {
			System.out.println("숫자 입력해주세요");
			return getUserWeight();
		}
	}

	public static double calculate(double height, double weight) {

		double BMI = weight / ((height / 100) * (height / 100));

		BMI = Math.round(BMI * 100) / 100.0;
		return BMI;
	}

	public static String judgment(double BMI) {

		String judgment = "";
		if (BMI > 0 && BMI < 20) {
			judgment = "저체중";
		} else if (BMI <= 24) {
			judgment = "정상";
		} else if (BMI < 30) {
			judgment = "과체중";
		} else if (BMI >= 30) {
			judgment = "비만";
		}

		return judgment;
	}

	public static void sout(String name, String gender, double height, double weight, double BMI, String judgment) {

		System.out.println("-----------BMI 계산 결과--------------");
		System.out.println("이름 : " + name);
		System.out.println("성별 : " + gender);
		System.out.println("키 : " + height);
		System.out.println("몸무게 : " + weight);
		System.out.println("BMI지수 : " + BMI);
		System.out.println("판정결과 : " + judgment);
		System.out.println("------------------------------------");
	}

}
