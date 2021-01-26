package demo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(JdbcConfig.class);

		UserDao dao = ctx.getBean(UserDao.class);

		User user = new User("Max", 35);
		user.setId(1);
		dao.update(user);

//		dao.deleteById(9);

		System.out.println("all users!");
		dao.findAll().forEach(u -> System.out.println(u));
		System.out.println("found:");
		System.out.println(dao.findById(10));
	}
}
