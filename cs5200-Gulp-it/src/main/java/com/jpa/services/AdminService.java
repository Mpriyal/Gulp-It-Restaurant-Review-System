package com.jpa.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.AdminDao;
import com.jpa.models.User;

@RestController
public class AdminService {

	@RequestMapping("/api/admin/users")
	public List<User> findAllUsers() {
		AdminDao admindao = AdminDao.getInstance();
		return admindao.UserfindAllUsers();
	}
	
	@RequestMapping("/api/admin/users/{UserId}")
	public int deleteUser(@PathVariable(name="UserId") int UserId) {
		AdminDao admindao = AdminDao.getInstance();
		return admindao.deleteUserById(UserId);
	}
}
