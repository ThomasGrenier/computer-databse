package com.excilys.persistence;

import com.excilys.model.UserModel;

public interface UserDAO {
	
	void create(UserModel user);

	UserModel getByLogin(String login);
}
