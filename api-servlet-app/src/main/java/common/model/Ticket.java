package common.model;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="ticket")
public class Ticket implements Serializable {
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
//    @SequenceGenerator(name="id_generator", sequenceName = "ticket_id_seq", allocationSize = 1)
	private int ticket_id;
	
//	@ManyToOne
//	@JoinColumn(name = "emp_id", insertable = false, updatable = false)
	private int emp_id;

	@Column
	private String type;
	
	@Column
	private double amount;
	
	@Column
	private String description;
	
	@Column(name="timestamp")
	private Date timestamp;

	public Ticket() {
		super();
	}

	public Ticket(String title, Date timestamp) {
		super();
		this.type = title;
		this.timestamp = timestamp;
	}
	
	public int getEmployeeId() {
		return emp_id;
	}
	
	public void setEmployeeId(int id) {
		this.emp_id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTicketId() {
		return ticket_id;
	}

	public void setTicketId(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = new Date();
	}

	@Override
	public String toString() {
		return "[" + this.type + "," + this.timestamp + "]";
	}
}
