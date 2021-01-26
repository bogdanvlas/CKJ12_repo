package demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

	private JdbcTemplate jdbc;

	@Autowired
	public UserDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public List<User> findAll() {
		String sql = "SELECT * FROM users WHERE age > ?";
		RowMapper<User> mapper = new UserMapper();
		List<User> users = jdbc.query(sql, mapper, 30);

		return users;
	}

	public void create(User user) {
		String sql = "INSERT INTO users(name, age) VALUES (?,?)";
		jdbc.update(sql, user.getName(), user.getAge());
	}

	public User findById(int id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		RowMapper<User> mapper = new UserMapper();
		try {
			User user = jdbc.queryForObject(sql, mapper, id);
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public void deleteById(int id) {
		String sql = "DELETE FROM users WHERE id = ?";
		jdbc.update(sql, id);
	}

	public void update(User user) {
		String sql = "";
		if (findById(user.getId()) == null) {
			sql = "INSERT INTO users VALUES (?,?,?)";
			jdbc.update(sql, user.getId(), user.getName(), user.getAge());
		} else {
			sql = "UPDATE users SET name = ?, age = ? WHERE id = ?";
			jdbc.update(sql, user.getName(), user.getAge(), user.getId());
		}
	}
}
