package demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLogic {
	private UserDao dao;

	@Autowired
	public UserLogic(UserDao dao) {
		this.dao = dao;
	}

	public ArrayList<User> findAll() {
		return dao.findAll();
	}

	public User findById(int id) {
		return dao.findById(id);
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}

	public boolean contains(User user) {
		int id = user.getId();
		return user.equals(dao.findById(id));
	}
}
