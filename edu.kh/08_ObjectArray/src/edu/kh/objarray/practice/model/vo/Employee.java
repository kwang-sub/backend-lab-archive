package edu.kh.objarray.practice.model.vo;

public class Employee {
	private int empNo;
	private	String empName;
	private	String department;
	private	String position;
	private	int salary;
	
	
	public Employee() {
		
	}
	public Employee(int empNo, String empName, String department, String position, int salary) {
		
		this.empNo = empNo;
		this.empName = empName;
		this.department = department;
		this.position = position;
		this.salary = salary;
	}





	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String toString() {
		String result = "사번 : "+empNo + ", 이름 : " + empName + ", 부서 : " + department + ", 직급 : " + position + ", 급여 : "+ salary;
		return result;
	}
	
}
