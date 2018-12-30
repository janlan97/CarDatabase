package parking.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionHandler {
	public static SessionHandler instance;
	private static SessionFactory sessionFactory;

	private SessionHandler() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public static SessionHandler getInstance() {
		return instance == null ? new SessionHandler() : instance;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session handle(String type) {
		if (type.equals("current")) {
			return sessionFactory.getCurrentSession();
		} else if (type.equals("open")) {
			return sessionFactory.openSession();
		} else {
			return null;
		}
	}
}