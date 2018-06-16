package hibernate.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import hibernate.pojo.Product;

public class testPaging {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		String name = "iphone";

		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.like("name", "%" + name + "%"));
		criteria.setFirstResult(2);
		criteria.setMaxResults(5);

		List<Product> products = criteria.list();
		for (Product p : products) {
			System.out.println(p.getName());
		}

		session.getTransaction().commit();
		session.close();
		sFactory.close();
	}

}
