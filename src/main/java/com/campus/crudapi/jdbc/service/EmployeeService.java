package com.campus.crudapi.jdbc.service;

import java.util.List;

import com.campus.crudapi.jdbc.dao.EmployeeDao;
import com.campus.crudapi.jdbc.dto.Employee;

public class EmployeeService {
	
	private EmployeeDao dao=new EmployeeDao();
	
	  
	    public Employee saveEmployee(Employee emp) {
	        return dao.saveEmployeeDao(emp);
	    }

	   
	    public Employee updateEmployee(Employee emp) {
	        return dao.updateEmployeeDao(emp);
	    }

	  
	    public String deleteEmployee(int id) {
	        return dao.deleteEmployeeDao(id);
	    }

	    public List<Employee> saveAllEmployees(List<Employee> empList) {
	        return dao.saveMultipleEmployeeDao(empList);
	    }
	    
	    public boolean deleteAllEmployees(List<Integer> empList) {
	        return dao.deleteMultipleEmployeeDao(empList);
	    }

	    public Employee getEmployeeById(int id) {
	        return dao.getEmployeeByIdDao(id);
	    }
	    
	    public List<Employee> fetchAllEmployees() {
	        return dao.fetchEmployeeDataDao();
	    }
}
