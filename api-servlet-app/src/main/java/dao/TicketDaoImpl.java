package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import common.model.Menu;
import common.model.Ticket;
import common.util.DBUtil;

public class TicketDaoImpl implements TicketDao {

	@Override
	public void create(Ticket ticket) {

		Session session = DBUtil.getInstance().getSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(ticket);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;// new RuntimeException(e.getCause());
		}

		session.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> findAll() {

		Session session = DBUtil.getInstance().getSession();

		Query query = session.createQuery("FROM common.model.Ticket");

		List<Ticket> tickets = query.list();

		session.close();

		return tickets;
	}

	@Override
	public Ticket findById(int id) {
		Session session = DBUtil.getInstance().getSession();

		Query query = session.createQuery("FROM common.model.Ticket where id = :id");
		query.setInteger("id", id);
		
		Ticket ticket = (Ticket) query.uniqueResult();

		session.close();

		return ticket;
	}

	@Override
	public void delete(int id) {
		Session session = DBUtil.getInstance().getSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(session.get(Menu.class, new Integer(id)));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;// new RuntimeException(e.getCause());
		}

		session.close();
	}

}
