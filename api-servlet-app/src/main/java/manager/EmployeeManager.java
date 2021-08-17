package manager;

import common.model.Employee;
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;

public class EmployeeManager {
	
	EmployeeDao dao = new EmployeeDaoImpl();

	public EmployeeManager() {}
	
	public void create(Employee user) {
		dao.create(user);
	}
	
	public Employee login(String username, String password) {
		if(dao.getEmployee(username, password) != null) {
			return dao.getEmployee(username, password);
		}
		return new Employee();
	}

}
