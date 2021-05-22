package net.developia.mospo.models;

import java.sql.SQLException;

public interface UserDAO {
	public UserDTO userLogin(UserDTO user) throws SQLException;

	public void userSignup(UserDTO userDTO) throws SQLException;
}
