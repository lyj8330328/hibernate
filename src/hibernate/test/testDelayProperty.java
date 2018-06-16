package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Product;

public class testDelayProperty {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		Product product = (Product) session.load(Product.class, 1);
		System.out.println("log1");
		System.out.println(product.getName());
		System.out.println("log2");

		session.getTransaction().commit();
		session.close();
		System.out.println(product.getName());
		sFactory.close();
		System.out.println(product.getName());
	}

}
