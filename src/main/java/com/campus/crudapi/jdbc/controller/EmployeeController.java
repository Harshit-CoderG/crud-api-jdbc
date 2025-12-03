package com.campus.crudapi.jdbc.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import com.campus.crudapi.jdbc.dto.Employee;
import com.campus.crudapi.jdbc.service.EmployeeService;

public class EmployeeController {
	private static EmployeeService service = new EmployeeService();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			System.out.println("\n===== EMPLOYEE CRUD APPLICATION =====");
			System.out.println("1. Add Employee");
			System.out.println("2. Add Multiple Employee");
			System.out.println("3. Update Employee");
			System.out.println("4. Delete Employee");
			System.out.println("5. Delete Multiple Employee");
			System.out.println("6. Get Employee by ID");
			System.out.println("7. Get All Employees");
			System.out.println("8. Exit");

			System.out.print("Enter option: ");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				addEmployee();
				break;
			case 2:
				addMultipleEmployee();
				break;

			case 3:
				updateEmployee();
				break;

			case 4:
				deleteEmployee();
				break;

			case 5:
				deleteMultipleEmployee();
				break;

			case 6:
				getEmployeeById();
				break;

			case 7:
				getAllEmployees();
				break;

			case 8:
				System.out.println("Exiting...");
				sc.close();
				System.exit(0);
				break;

			default:
				System.out.println("Invalid choice! Try again.");
			}
		}

	}

	private static void addEmployee() {

		System.out.println("\nEnter Employee Details:");

		System.out.print("ID: ");
		int id = sc.nextInt();
		sc.nextLine(); // consume newline

		System.out.print("Name: ");
		String name = sc.nextLine();

		System.out.print("Email: ");
		String email = sc.nextLine();

		System.out.print("Password: ");
		String password = sc.nextLine();

		System.out.print("Phone: ");
		long phone = sc.nextLong();
		sc.nextLine(); // consume newline

		System.out.print("Date of Joining (yyyy-mm-dd): ");
		LocalDate doj = LocalDate.parse(sc.nextLine());

		System.out.print("Designation: ");
		String designation = sc.nextLine();

		System.out.print("Gender: ");
		String gender = sc.nextLine();

		// Create employee object
		Employee emp = new Employee();
		emp.setId(id);
		emp.setName(name);
		emp.setEmail(email);
		emp.setPassword(password);
		emp.setPhone(phone);
		emp.setDoj(doj);
		emp.setDesignation(designation);
		emp.setGender(gender);

		// Save to DB
		Employee dbEmp = service.saveEmployee(emp);

		String msg = (dbEmp != null) ? "Data Stored Successfully!" : "Something went wrong!";
		System.out.println(msg);
	}

	private static void addMultipleEmployee() {

		System.out.print("How many employees do you want to add? ");
		int count = sc.nextInt();
		sc.nextLine(); // consume newline

		List<Employee> employees = new ArrayList<>();

		for (int i = 1; i <= count; i++) {
			System.out.println("\nEnter details for Employee " + i + ":");

			System.out.print("ID: ");
			int id = sc.nextInt();
			sc.nextLine();

			System.out.print("Name: ");
			String name = sc.nextLine();

			System.out.print("Email: ");
			String email = sc.nextLine();

			System.out.print("Password: ");
			String password = sc.nextLine();

			System.out.print("Phone: ");
			long phone = sc.nextLong();
			sc.nextLine();

			System.out.print("Date of Joining (YYYY-MM-DD): ");
			String dojInput = sc.nextLine();
			LocalDate doj = LocalDate.parse(dojInput);

			System.out.print("Designation: ");
			String designation = sc.nextLine();

			System.out.print("Gender: ");
			String gender = sc.nextLine();

			// Create employee object
			Employee emp = new Employee(id, name, email, password, phone, doj, designation, gender);

			employees.add(emp);
		}

		// Save all employees
		List<Employee> savedList = service.saveAllEmployees(employees);

		if (savedList != null && !savedList.isEmpty()) {
			System.out.println("\nEmployees saved successfully:");
			for (Employee e : savedList) {
				System.out.println(e);
			}

		} else {
			System.out.println("Something went wrong!");

		}
	}

	private static void updateEmployee() {

		System.out.println("\nEnter Employee ID to Update:");
		System.out.print("ID: ");
		int id = sc.nextInt();
		sc.nextLine(); // consume leftover newline

		System.out.println("Enter New Employee Details:");

		System.out.print("Name: ");
		String name = sc.nextLine();

		System.out.print("Email: ");
		String email = sc.nextLine();

		System.out.print("Password: ");
		String password = sc.nextLine();

		System.out.print("Phone: ");
		long phone = sc.nextLong();
		sc.nextLine(); // consume newline

		System.out.print("Date of Joining (yyyy-mm-dd): ");
		LocalDate doj = LocalDate.parse(sc.nextLine());

		System.out.print("Designation: ");
		String designation = sc.nextLine();

		System.out.print("Gender: ");
		String gender = sc.nextLine();

		// Prepare updated employee object
		Employee emp = new Employee();
		emp.setId(id);
		emp.setName(name);
		emp.setEmail(email);
		emp.setPassword(password);
		emp.setPhone(phone);
		emp.setDoj(doj);
		emp.setDesignation(designation);
		emp.setGender(gender);

		// Call service for update
		Employee resEmp = service.updateEmployee(emp);

		if (resEmp != null) {
			System.out.println(resEmp.getName() + "'s data has been updated successfully!");
		} else {
			System.out.println("Something went wrong... Data not updated.");
		}
	}

	private static void deleteEmployee() {

		System.out.print("Enter Employee ID to delete: ");
		int id = sc.nextInt();

		String result = service.deleteEmployee(id);
		System.out.println(result);
	}

	private static void deleteMultipleEmployee() {

		System.out.print("\nHow many employees do you want to delete? ");
		int count = sc.nextInt();

		List<Integer> employeeIds = new ArrayList<>();

		for (int i = 1; i <= count; i++) {
			System.out.print("Enter Employee ID " + i + ": ");
			int id = sc.nextInt();
			employeeIds.add(id);
		}

		boolean isDeleted = service.deleteAllEmployees(employeeIds);

		if (isDeleted) {
			System.out.println("\nEmployees deleted successfully!");
		} else {
			System.out.println("\nFailed to delete some or all employees.");
		}
	}

	private static void getAllEmployees() {

		List<Employee> empList = service.fetchAllEmployees();
		if (empList != null) {
			for (Employee e : empList) {
				System.out.println(e);
			}
		} else {
			System.out.println("No Data Found");
		}
	}

	private static void getEmployeeById() {

		System.out.print("Enter Employee ID to search: ");
		int id = sc.nextInt();

		Employee emp = service.getEmployeeById(id);

		if (emp != null) {
			System.out.println("\nEmployee Found:");
			System.out.println(emp);
		} else {
			System.out.println("Employee not found!");
		}
	}
}
