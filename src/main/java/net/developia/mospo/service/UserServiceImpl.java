package net.developia.mospo.service;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.developia.mospo.models.UserDAO;
import net.developia.mospo.models.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserDAO userDAO;
	

	@Override
	public UserDTO userLogin(UserDTO userDTO) throws SQLException {
		UserDTO user = userDAO.userLogin(userDTO);
		if(user == null) {
			throw new RuntimeException("���̵� Ȥ�� ��й�ȣ�� Ʋ���ϴ�.");
		}
		return user;
	}
	@Override
	public void userSignup(UserDTO userDTO) throws SQLException {
		userDAO.userSignup(userDTO);
		
	}
}
