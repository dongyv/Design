package dongyv.xch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dongyv.xch.dao.UserDao;
import dongyv.xch.model.Users;

@Service
public class UserService {
	@Autowired
	public UserDao userDao;
	
	public void insert(Users users) {
		userDao.insert(users);
	}
}
