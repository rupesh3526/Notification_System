package com.rupesh.notification.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rupesh.notification.entity.UserPreference;
import com.rupesh.notification.enums.NotificationType;

@Repository
public interface UserPreferenceRepo extends JpaRepository<UserPreference, Integer>{
	 @Query("""
	           SELECT up.channel
	           FROM UserPreference up
	           WHERE up.user.id = :userId
	           """)
	    Set<NotificationType> findChannelsByUserId(@Param("userId") Integer userId);
}
