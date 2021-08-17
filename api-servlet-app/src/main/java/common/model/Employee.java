package common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name="employee")
public class Employee implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name="id_generator", sequenceName = "employee_id_seq", allocationSize = 1)
//	@OneToMany
//	@JoinColumn(name = "emp_id")
	private int emp_id;

	@Column
	private String fname;
	@Column
	private String lname;
	@Column
	private String username;
	@Column
	private String password;

	public Employee() {
	}

	public Employee(String fname, String lname, String username, String password) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getId() {
		return emp_id;
	}

	public void setId(int id) {
		this.emp_id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
