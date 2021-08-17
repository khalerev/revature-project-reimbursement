package manager;

import java.util.List;

import common.model.Menu;
import common.model.Ticket;
import dao.TicketDao;
import dao.TicketDaoImpl;

public class TicketManager {

	private TicketDao dao = new TicketDaoImpl();
	
	public TicketManager() {
	}
	
	public void create(Ticket ticket) {
		dao.create(ticket);
	}
	
	public List<Ticket> findAll() {
		return dao.findAll();
	}

	public static void main(String[] args) {
		List<Ticket> tickets = new TicketManager().findAll();
		System.out.println(tickets);
	}

	public Ticket findById(int id) {
		return dao.findById(id);
	}

	public void delete(int id) {
		dao.delete(id);
	}
}
