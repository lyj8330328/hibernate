package hibernate.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Category;
import hibernate.pojo.Product;

public class testOneToMany {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Category");
		List<Category> categories = query.list();
		for (Category c : categories) {
			System.out.println("属于" + c.getName() + "类的商品有：");
			for (Product p : c.getProducts()) {
				System.out.println(p.getName());
			}
		}

		session.getTransaction().commit();
		session.close();
		sFactory.close();
	}

}
