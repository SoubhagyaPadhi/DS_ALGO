package com.skp.demo;

class Address implements Cloneable{

	String city;
	String houseNumber;
	String lane;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public Address(String city, String houseNumber, String lane) {
		super();
		this.city = city;
		this.houseNumber = houseNumber;
		this.lane = lane;
	}
	
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

}

class Employee implements Cloneable {

	String empNo;
	String empName;
	Address empAddr;

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Address getEmpAddr() {
		return empAddr;
	}

	public void setEmpAddr(Address empAddr) {
		this.empAddr = empAddr;
	}

	public Employee(String empNo, String empName, Address empAddr) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.empAddr = empAddr;
	}

	protected Object clone() throws CloneNotSupportedException {
		Employee emp = (Employee) super.clone();
		
		Address address = (Address) emp.getEmpAddr().clone();
		
		emp.setEmpAddr(address);
		return emp;

	}

}

public class CloaningJavaTest {

	public static void main(String[] args) throws CloneNotSupportedException {

		Address address = new Address("RKL", "21", "RKLNagar");
		Employee e1 = new Employee("122", "SKP", address);
		
		Employee e2 = (Employee) e1.clone();
		
		e2.setEmpName("PKS");
		e2.getEmpAddr().setCity("JSG");
		
		System.out.println(e1.getEmpAddr().getCity());
		System.out.println(e2.getEmpAddr().getCity());
	}

}
