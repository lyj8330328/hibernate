package hibernate.test;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Category;
import hibernate.pojo.Product;

public class testDelayRelation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		Category category = (Category) session.get(Category.class, 4);
		System.out.println("log1");
		Set<Product> products = category.getProducts();
		Iterator<Product> iterator = products.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getName());

		}
		System.out.println("log2");

		session.getTransaction().commit();
		session.close();
		sFactory.close();
	}

}
