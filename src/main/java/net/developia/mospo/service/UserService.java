package net.developia.mospo.service;

import java.sql.SQLException;

import net.developia.mospo.models.UserDTO;

public interface UserService   {
	UserDTO userLogin(UserDTO user) throws SQLException;
	void userSignup(UserDTO userDTO) throws SQLException;
}
