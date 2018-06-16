package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Category;
import hibernate.pojo.Product;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		for (int i = 1; i <= 10; i++) {
			Product product = new Product();
			if (i == 10) {
				product.setName("iphone X");
			} else {
				product.setName("iphone " + i);
			}
			product.setPrice(i * 1000);
			session.save(product);
		}

		for (int i = 1; i <= 5; i++) {
			Category category = new Category();
			category.setName("C" + i);
			session.save(category);
		}
		session.getTransaction().commit();
		session.close();
		sFactory.close();
	}

}
