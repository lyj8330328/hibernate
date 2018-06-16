package hibernate.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import hibernate.pojo.Product;

public class testHQLCriteriaSQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		String name = "iphone";

		Query query = session.createQuery("from Product p where p.name like ?");
		query.setString(0, "%" + name + "%");
		List<Product> products = query.list();
		for (Product p : products) {
			System.out.print(p.getId() + "\t");
			System.out.print(p.getName() + "\t");
			System.out.print(p.getPrice());
			System.out.println();
		}

		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.like("name", "%" + name + "%"));
		List<Product> products2 = criteria.list();
		for (Product p : products2) {
			System.out.println(p.getName());
		}
		System.out.println("总数：" + criteria.list().size());

		String sqlString = "select * from Product p where p.name like '%" + name + "%'";
		Query query2 = session.createSQLQuery(sqlString);
		List<Object[]> list = query2.list();
		for (Object[] os : list) {
			for (Object filed : os) {
				System.out.print(filed + "\t");
			}
			System.out.println();
		}
		session.getTransaction().commit();
		session.close();
		sFactory.close();
	}

}
