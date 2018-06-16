package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.pojo.Product;

public class test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();

		Product product = new Product();
		product.setName("test2");
		System.out.println("此时product是临时状态");
		/*
		 * 通过new创建对象后，对象并没有立刻持久化，它并未与数据库中的数据有任何关联，此时Java对象的状态为瞬时状态。
		 * Session对于瞬时状态的Java对象是一无所知的，当对象不再被其他对象引用时，它的所有数据也就丢失了，
		 * 对象将会被Java虚拟机按照垃圾回收机制处理。
		 */

		session.save(product);
		System.out.println("此时product是持久状态");
		/*
		 * 对象与Session关联，被Session管理时，它就处于持久状态。处于持久状态的对象拥有数据库标识（数据库中的主键值）。
		 * 那么，对象是什么时候与Session发生关联的呢？有两种方法：
		 * 第一种，通过Sesison的查询接口，或者get()方法，或者load()方法从数据库中加载对象的时候，
		 * 加载的对象是与数据库表中的一条记录关联的，此时对象与加载它的Session发生关联；
		 * 第二种，瞬时状态的对象，通过Session的save()方法或SaveOrUpdate()方法时，Java对象也与Session发生关联。
		 * 对于处于持久状态的对象，Session会持续跟踪和管理它们，如果对象的内部状态发生了任何变更，Hibernate会选择合适的时机（
		 * 如事务提交时）将变更固化到数据库中。
		 */
		session.getTransaction().commit();
		session.close();
		System.out.println("此时product是游离状态");
		/*
		 * 处于持久状态的对象，脱离与其关联的Session的管理后，对象就处于游离状态。
		 * 处于游离状态的对象，Session无法保证对象所包含的数据与数据库中的记录一直，因为Hibernate已经无法感知对该对象的任何操作。
		 * Session提供了两个方法（update()、merge()），将处于游离状态的对象，与一个新的Session发生关联。
		 * 此时，对象的状态就从游离状态重新转换为持久状态。
		 */

		sFactory.close();
	}

}
