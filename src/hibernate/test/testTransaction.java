package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Product;

public class testTransaction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		Product product = (Product) session.get(Product.class, 1);
		session.delete(product);

		Product product2 = (Product) session.get(Product.class, 2);
		product2.setName("iphoneiphoneiphoneiphoneiphoneiphoneiphoneiphoneiphone");
		session.update(product2);

		session.getTransaction().commit();
		session.close();
		sFactory.close();
	}

}
