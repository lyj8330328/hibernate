package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Product;

public class testOptimisticLock {
	/*
	 * 场景描述 1. 通过session1得到id=1的对象 product1 2. 在product1原来价格的基础上增加1000
	 * 3.更新product1之前，通过session2得到id=1的对象product2 4. 在product2原来价格的基础上增加1000
	 * 5.更新product1 6. 更新product2
	 */

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		Session session2 = sFactory.openSession();

		session.beginTransaction();
		session2.beginTransaction();

		Product product = (Product) session.get(Product.class, 1);
		System.out.println("产品原价为：" + product.getPrice());
		product.setPrice(product.getPrice() + 1000);

		Product product2 = (Product) session2.get(Product.class, 1);
		product2.setPrice(product2.getPrice() + 1000);

		session.update(product);
		session2.update(product2);

		session.getTransaction().commit();
		session2.getTransaction().commit();

		Product product3 = (Product) session.get(Product.class, 1);
		System.out.println("最终的价格：" + product3.getPrice());

		session.close();
		session2.close();
		sFactory.close();
	}

}
