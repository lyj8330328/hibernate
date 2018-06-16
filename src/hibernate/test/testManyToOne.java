package hibernate.test;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Category;
import hibernate.pojo.Product;

public class testManyToOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		for (int i = 1; i <= 5; i++) {
			Category category = new Category();
			category.setName("C" + i);
			session.save(category);
		}

		Query query2 = session.createQuery("from Category");
		List<Category> categorys = query2.list();

		for (int i = 1; i <= 10; i++) {
			Random random = new Random();
			Product product = (Product) session.get(Product.class, i);
			product.setCategory(categorys.get(random.nextInt(5)));
			session.update(product);
		}

		session.getTransaction().commit();
		session.close();
		sFactory.close();
	}

}
