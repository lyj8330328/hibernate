package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Category;

public class testCascadeDelete {
	/*
	 * 级联有4种类型： all：所有操作都执行级联操作； none：所有操作都不执行级联操作； delete：删除时执行级联操作；
	 * save-update：保存和更新时执行级联操作；
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		Category category = (Category) session.get(Category.class, 2);
		session.delete(category);

		session.getTransaction().commit();
		session.close();
		sFactory.close();
	}

}
