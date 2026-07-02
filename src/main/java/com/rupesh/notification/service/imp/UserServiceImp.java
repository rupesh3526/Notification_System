package com.rupesh.notification.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rupesh.notification.entity.User;
import com.rupesh.notification.repo.UserRepo;
import com.rupesh.notification.service.UserService;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserRepo userRepo;
	 
	@Override
	public User getUserById(int id) {

		return userRepo.findById(id).orElse(null);
	}

}
