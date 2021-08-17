package dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import common.model.Employee;
import common.util.DBUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	public EmployeeDaoImpl() {
	}

	@Override
	public void create(Employee user) {
		Session session = DBUtil.getInstance().getSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;// new RuntimeException(e.getCause());
		}

		session.close();
	}

	@Override
	public Employee getEmployee(String username, String password) {
		try {
			Session session = DBUtil.getInstance().getSession();

			Query query = session.createQuery("FROM common.model.Employee where username = :uname and password = :pswd");
			query.setString("uname", username);
			query.setString("pswd", password);

			Employee user = (Employee) query.uniqueResult();

			session.close();
			return user;
		}
		catch (Exception ex) {
			throw ex;
		}
	}
	
//	public static void main(String[] args) {
//		UserDaoImpl impl = new UserDaoImpl();
//		//impl.create(new User("vimal","test123"));
//		User obj = impl.getUser("vimal", "test123");
//		System.out.println(obj);
//	}

}
