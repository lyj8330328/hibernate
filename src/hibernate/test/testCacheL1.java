package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Category;

public class testCacheL1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		System.out.println("log1");
		Category category = (Category) session.get(Category.class, 1);
		System.out.println("log2");
		Category category2 = (Category) session.get(Category.class, 1);
		System.out.println("log3");

		session.getTransaction().commit();
		session.close();
		sFactory.close();
	}

}
