package com.campus.crudapi.jdbc.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Employee implements Serializable {
	private int id;
	private String name;
	private String email;
	private String password;
	private long phone;
	private LocalDate doj;
	private String designation;
	private String gender;
	
	public Employee(int id, String name, String email, String password, long phone, LocalDate doj, String designation, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.doj = doj;
		this.designation = designation;
		this.gender = gender;
	}

	public Employee() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone="
				+ phone + ", doj=" + doj + ", designation=" + designation + ", gender=" + gender + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(designation, doj, email, gender, id, name, password, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(designation, other.designation) && Objects.equals(doj, other.doj)
				&& Objects.equals(email, other.email) && Objects.equals(gender, other.gender) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password) && phone == other.phone;
	}
	
	

}
