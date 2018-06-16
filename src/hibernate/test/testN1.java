package hibernate.test;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Product;

public class testN1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		String name = "iphone";
		Query query = session.createQuery("from Product p where p.name like ?");
		query.setString(0, "%" + name + "%");

		Iterator<Product> iterator = query.iterate();
		while (iterator.hasNext()) {
			Product product = (Product) iterator.next();
			System.out.println(product.getName());

		}

		Query query2 = session.createQuery("select count(*) from Product p where p.name like ?");
		query2.setString(0, "%" + name + "%");

		long total = (Long) query2.uniqueResult();
		System.out.println(total);

		session.getTransaction().commit();
		session.close();
		sFactory.close();
	}

}
