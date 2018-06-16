package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Category;
import hibernate.pojo.Product;

public class testCascadeSaveUpdate {
	/*
	 * 级联有4种类型： all：所有操作都执行级联操作； none：所有操作都不执行级联操作； delete：删除时执行级联操作；
	 * save-update：保存和更新时执行级联操作；
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		Category category = (Category) session.get(Category.class, 5);
		Product product = new Product();
		product.setName("xiaomi_note");
		Product product2 = new Product();
		product2.setName("xiaomi_max");
		Product product3 = new Product();
		product3.setName("xiaomi_8");

		category.getProducts().add(product);
		category.getProducts().add(product2);
		category.getProducts().add(product3);

		session.getTransaction().commit();
		session.close();
		sFactory.close();
	}

}
