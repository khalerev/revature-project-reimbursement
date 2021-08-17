package dao;

import common.model.Employee;

public interface EmployeeDao {

	public void create(Employee user);
	public Employee getEmployee(String username, String password);

}
