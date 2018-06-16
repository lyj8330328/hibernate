package hibernate.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Product;
import hibernate.pojo.User;

public class testManyToMany {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();
		// 添加三个用户
		Set<User> users = new HashSet<User>();
		for (int i = 0; i < 3; i++) {
			User user = new User();
			user.setName("user" + i);
			users.add(user);
			session.save(user);
		}
		// 一件商品多个人购买
		Product p1 = (Product) session.get(Product.class, 2);
		p1.setUsers(users);
		session.save(p1);

		// 一个人购买多个商品
		// 获取商品列表

		User user = new User();
		user.setName("user3");
		session.save(user);

		Query query = session.createQuery("from Product");
		List<Product> products = query.list();
		Set<Product> products2 = new HashSet<Product>();
		for (int i = 0; i < products.size(); i++) {
			products2.add(products.get(i));
		}
		User u1 = (User) session.get(User.class, 4);
		u1.setProducts(products2);
		session.save(u1);

		session.getTransaction().commit();
		session.close();
		sFactory.close();
	}

}
