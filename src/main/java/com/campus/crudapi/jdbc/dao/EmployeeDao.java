package com.campus.crudapi.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.campus.crudapi.jdbc.connection.EmployeeConnection;
import com.campus.crudapi.jdbc.dto.Employee;

public class EmployeeDao {

	
//insert method
	public Employee saveEmployeeDao(Employee emp) {
		Connection con =null;
		PreparedStatement ps = null;
		String insertEmployeeQuery = "insert into employee(id,name,email,password,phone,doj,designation,gender) values(?,?,?,?,?,?,?,?)";
		try {
			con=EmployeeConnection.getEmployeeConnection();
			ps = con.prepareStatement(insertEmployeeQuery);
			ps.setInt(1, emp.getId());
			ps.setString(2, emp.getName());
			ps.setString(3, emp.getEmail());
			ps.setString(4, emp.getPassword());
			ps.setLong(5, emp.getPhone());
			ps.setObject(6, emp.getDoj());
			ps.setString(7, emp.getDesignation());
			ps.setString(8, emp.getGender());

			int a = ps.executeUpdate();
			return a != 0 ? emp : null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
		
				try {
					if (ps != null) ps.close();
					if (con != null) con.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			
		}
	}
//fetch method
	public List<Employee> fetchEmployeeDataDao() {
		String displayEmployeeQuery = "Select * from employee";
		Connection con =null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con=EmployeeConnection.getEmployeeConnection();
			ps = con.prepareStatement(displayEmployeeQuery);

			rs = ps.executeQuery();
			List<Employee> empList = new ArrayList<Employee>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				long phone = rs.getLong("phone");
				LocalDate doj = (LocalDate) rs.getDate("doj").toLocalDate();
				String designation = rs.getString("designation");
				String gender = rs.getString("gender");

				Employee emp = new Employee();
				emp.setId(id);
				emp.setName(name);
				emp.setEmail(email);
				emp.setPassword(password);
				emp.setPhone(phone);
				emp.setDoj(doj);
				emp.setDesignation(designation);
				emp.setGender(gender);

				empList.add(emp);
			}
			return empList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			
				try {
					if (rs != null) rs.close();
					if (ps != null) ps.close();
					if (con != null) con.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			
		}

	}
//	********************************************************************************************
//save multiple method
	public List<Employee> saveMultipleEmployeeDao(List<Employee> emps) {
		Connection con =null;
		PreparedStatement ps = null;
		String insertEmployeeQuery = "insert into employee(id,name,email,password,phone,doj,designation,gender) values(?,?,?,?,?,?,?,?)";

		try {
			con=EmployeeConnection.getEmployeeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(insertEmployeeQuery);

			for (Employee emp : emps) {
				ps.setInt(1, emp.getId());
				ps.setString(2, emp.getName());
				ps.setString(3, emp.getEmail());
				ps.setString(4, emp.getPassword());
				ps.setLong(5, emp.getPhone());
				ps.setObject(6, emp.getDoj());
				ps.setString(7, emp.getDesignation());
				ps.setString(8, emp.getGender());
				ps.addBatch();

			}

			int a[] = ps.executeBatch();
			con.commit();
			if (a.length != 0) {
				System.out.println(a.length + " row affected in db table");
				return emps;

			}
			return null;
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			return null;
		} finally {
			try {
				
				if (ps != null) ps.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

//	********************************************************************************************
//delete method
	public String deleteEmployeeDao(int id) {
		Connection con =null;
		PreparedStatement ps = null;

		try {

			String deleteStudentQuery = "delete from employee where id=?";
			con=EmployeeConnection.getEmployeeConnection();
			ps = con.prepareStatement(deleteStudentQuery);
			ps.setInt(1, id);
			int a = ps.executeUpdate();
			
			if (a > 0) {
				return "Data deleted";
			} else {
				return "Something went wrong or given  id not found!!!";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				if (ps != null) ps.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

//	********************************************************************************************
//delete multiple method
	public boolean deleteMultipleEmployeeDao(List<Integer> employeeId) {
		Connection con =null;
		PreparedStatement ps = null;

		String deleteQuery = "delete from employee where id=?";

		try {
			con=EmployeeConnection.getEmployeeConnection();

			con.setAutoCommit(false);

			ps = con.prepareStatement(deleteQuery);
			for (Integer id : employeeId) {
				ps.setInt(1, id);
				ps.addBatch();
			}
			int[] a = ps.executeBatch();
			con.commit();
			if (a.length != 0) {
				System.out.println(a.length + " row affected in db table");
				return true;
			}
			return false;

		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			return false;

		} finally {
			try {
				if (ps != null) ps.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

//	Update method

	public Employee updateEmployeeDao(Employee emp) {

		String updateEmployeeQuery = "update employee set name=? ,email=?, password=?,phone=?,doj=? , designation=? , gender=? where id=?";
		Connection con =null;
		PreparedStatement ps = null;

		try {
			con=EmployeeConnection.getEmployeeConnection();
			ps = con.prepareStatement(updateEmployeeQuery);
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getEmail());
			ps.setString(3, emp.getPassword());
			ps.setLong(4, emp.getPhone());
			ps.setObject(5, emp.getDoj());
			ps.setString(6, emp.getDesignation());
			ps.setString(7, emp.getGender());
			ps.setInt(8, emp.getId());

			int a = ps.executeUpdate();

			return a != 0 ? emp : null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
				try {
					if (ps != null) ps.close();
					if (con != null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
	}
//	get employee by id
	public Employee getEmployeeByIdDao(int id1) {

		String displayEmployeeQuery = "select * from employee where id=?";
		Connection con =null;
		ResultSet set = null;

		PreparedStatement ps = null;

		try {
			con=EmployeeConnection.getEmployeeConnection();
			ps = con.prepareStatement(displayEmployeeQuery);
			ps.setInt(1, id1);
			set = ps.executeQuery();
			if (set.next()) {
				int id = set.getInt("id");
				String name = set.getString("name");
				String email = set.getString("email");
				String Password = set.getString("password");
				long phone = set.getLong("phone");
				LocalDate doj = set.getDate("doj").toLocalDate();
				String designtion = set.getString("designation");
				String gender = set.getString("gender");
				
				Employee emp = new Employee();
				emp.setId(id);
				emp.setName(name);
				emp.setEmail(email);
				emp.setPassword(Password);
				emp.setPhone(phone);
				emp.setDoj(doj);
				emp.setDesignation(designtion);
				emp.setGender(gender);

				return emp;
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} finally {
		
				try {
					if (set != null) set.close();
					if (ps != null) ps.close();
					if (con != null) con.close();
				} catch (SQLException e) {

					e.printStackTrace();

				}

			

		}

	}
}
