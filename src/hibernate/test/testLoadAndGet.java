package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Product;

public class testLoadAndGet {

	/*
	 * load方式是延迟加载，只有属性被访问的时候才会调用sql语句 ;get方式是非延迟加载，无论后面的代码是否会访问到属性，马上执行sql语句
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();
		System.out.println("log1");
		Product product = (Product) session.get(Product.class, 1);
		System.out.println("log2");
		Product product2 = (Product) session.load(Product.class, 2);
		// Product product2 = (Product) session.load(Product.class, 200);
		System.out.println("log3");
		// System.out.println(product2.getName());
		System.out.println("log4");

		// Product p3 = (Product) session.get(Product.class, 500);
		// System.out.println("p3=" + p3.getName());

		// Product p4 = (Product) session.load(Product.class, 500);
		// System.out.println("p4=" + p4.getName());

		Product product3 = (Product) session.load(Product.class, 5);
		System.out.println(product3.getName());
		Product product4 = (Product) session.load(Product.class, 5);
		System.out.println(product4.getName());
		session.getTransaction().commit();
		session.close();
		System.out.println(product.getName());
		sFactory.close();
	}

}
