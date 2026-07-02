package com.rupesh.notification.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupesh.notification.entity.NotificationHistory;
@Repository
public interface NotificationHistoryRepo extends JpaRepository<NotificationHistory, Integer>{

}
