package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
	// CRUD
	// Create Read Update Delete

	private DataSource dataSource;

	@Autowired
	public UserDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// ���������� ������������ � �������
	public void add(User user) {
		// INSERT users(name, age) VALUES ('Ivan', 20)
		String name = user.getName();
		int age = user.getAge();

		String sql = "INSERT users(name, age) VALUES ('%s', %d)";
		sql = String.format(sql, name, age);

		try (Connection conn = dataSource.getConnection()) {
			Statement stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ��������� ���� �������������
	public ArrayList<User> findAll() {
		ArrayList<User> users = new ArrayList<>();

		try (Connection conn = dataSource.getConnection()) {
			Statement stat = conn.createStatement();

			String sql = "SELECT * FROM users";

			ResultSet results = stat.executeQuery(sql);

			while (results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				int age = results.getInt("age");
				User u = new User(name, age);
				u.setId(id);
				users.add(u);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return users;
	}

	// ��������� �� ��������������
	public User findById(int id) {
		User user = null;

		try (Connection conn = dataSource.getConnection()) {
			Statement stat = conn.createStatement();

			String sql = "SELECT name, age FROM users WHERE id = %d";
			sql = String.format(sql, id);

			ResultSet res = stat.executeQuery(sql);

			if (res.next()) {
				String name = res.getString("name");
				int age = res.getInt("age");
				user = new User(name, age);
				user.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void sendMoney(int senderId, int receiverId, int amount) {
		// ������ ������ � �����������
		String minusMoney = "UPDATE users SET balance = balance-%d WHERE id = %d";
		minusMoney = String.format(minusMoney, amount, senderId);
		// ������ ���������� ����������
		String plusMoney = "UPDATE users SET balance = balance+%d WHERE id = %d";
		plusMoney = String.format(plusMoney, amount, receiverId);

		// �������� ������ �������������
		String showUsers = "SELECT * FROM users";

		String getSenderId = "SELECT * FROM users WHERE id = " + senderId;
		String getReceiverId = "SELECT * FROM users WHERE id = " + receiverId;

		try (Connection conn = dataSource.getConnection()) {
			Statement stat = conn.createStatement();

			ResultSet sender = stat.executeQuery(getSenderId);
			boolean checkSender = sender.next();

			int senderBalance = sender.getInt("balance");

			boolean enoughBalance = true;
			if (senderBalance < amount) {
				enoughBalance = false;
			}

			ResultSet receiver = stat.executeQuery(getReceiverId);
			boolean checkReceiver = receiver.next();

			// ������ ����������!
			conn.setAutoCommit(false);
			System.out.println("Transaction begin");

			boolean queriesDone = true;

			try {
				// ��� ��������� ������
				stat.executeUpdate(minusMoney);
				stat.executeUpdate(plusMoney);
			} catch (SQLException e) {
				e.printStackTrace();
				queriesDone = false;
			}

			// ����� ����������
			if (checkSender && checkReceiver && queriesDone && enoughBalance) {
				conn.commit();
				System.out.println("Transaction commit");
			} else {
				conn.rollback();
				System.out.println("Transaction rollback!");
			}

			ResultSet rs = stat.executeQuery(showUsers);
			System.out.println(conn.getAutoCommit());

			while (rs.next()) {
				System.out.printf("%d %s %d\n", rs.getInt("id"), rs.getString("name"), rs.getInt("balance"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteById(int id) {
		try (Connection conn = dataSource.getConnection()) {
			Statement stat = conn.createStatement();

			String sql = "DELETE FROM users WHERE id = %d";
			sql = String.format(sql, id);
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
