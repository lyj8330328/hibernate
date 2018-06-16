package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Product;

public class testSession {

	static Session session;
	static Session session2;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		// Session session = sFactory.openSession();
		// Session session2 = sFactory.openSession();

		// Session session = sFactory.getCurrentSession();
		// Session session2 = sFactory.getCurrentSession();
		//
		// System.out.println(session == session2);

		// session.close();
		// session2.close();

		Thread thread = new Thread() {
			public void run() {
				session = sFactory.getCurrentSession();
			}
		};
		thread.start();

		Thread thread2 = new Thread() {
			public void run() {
				session2 = sFactory.getCurrentSession();
			}
		};
		thread2.start();

		thread.join();
		thread2.join();

		System.out.println(session == session2);

		Session session3 = sFactory.openSession();
		System.out.println(((Product) session3.get(Product.class, 2)).getName());

		// Session session4 = sFactory.getCurrentSession();
		// session4.get(Product.class, 2);

		session.close();
		session2.close();

		Session session5 = sFactory.getCurrentSession();
		session5.beginTransaction();
		session5.get(Product.class, 3);
		session5.getTransaction().commit();
		// session5.close();

		sFactory.close();
	}

}
