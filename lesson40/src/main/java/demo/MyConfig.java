package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan
public class MyConfig {
	// создание объекта DataSource
	@Bean
	public DataSource dataSource() {
		String url = "jdbc:mysql://localhost/my_demo?serverTimezone=UTC";
		String username = "root";
		String password = "";

		DataSource source = new DataSource(url, username, password);

		return source;
	}

//	// создание объекта UserDao
//	@Bean
//	public UserDao userDao() {
//		UserDao dao = new UserDao(dataSource());
//		return dao;
//	}
}
