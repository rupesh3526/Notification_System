package com.rupesh.notification.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupesh.notification.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
