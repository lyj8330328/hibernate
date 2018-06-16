package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Product;

public class test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		Product product = (Product) session.get(Product.class, 33);
		System.out.println("id=33的产品为：" + product.getName());

		product.setName("iphone-11");
		session.update(product);

		// session.delete(product);

		session.getTransaction().commit();
		session.close();
		sFactory.close();
	}

}
