package demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);

		
		DataSource ds = ctx.getBean(DataSource.class);

		UserDao dao = ctx.getBean(UserDao.class);

		UserLogic logic = ctx.getBean(UserLogic.class);

		logic.findAll().forEach(u -> System.out.println("---> " + u));

		User user = new User("Oleg", 10);
		user.setId(3);

		System.out.println(logic.contains(user));
	}
}
