package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Category;

public class testCacheL2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		Category category = (Category) session.get(Category.class, 1);
		System.out.println("log1");
		Category category2 = (Category) session.get(Category.class, 1);
		System.out.println("log2");
		session.getTransaction().commit();
		session.close();

		Session session2 = sFactory.openSession();
		session2.beginTransaction();
		Category category3 = (Category) session2.get(Category.class, 1);
		System.out.println("log3");

		// Category category4 = (Category) session2.load(Category.class, 1);
		// System.out.println(category4.getName());
		session2.getTransaction().commit();
		session2.close();

		sFactory.close();
	}

}
