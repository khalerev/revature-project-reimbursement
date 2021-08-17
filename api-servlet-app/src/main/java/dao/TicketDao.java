package dao;

import java.util.List;

import common.model.Ticket;

public interface TicketDao {

	public void create(Ticket ticket);
	
	public List<Ticket> findAll();

	public Ticket findById(int id);

	public void delete(int id);

}
