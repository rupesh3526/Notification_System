package com.rupesh.notification.service;

import com.rupesh.notification.dto.NotificationRequest;

public interface  NotificationService {
	void send(NotificationRequest nr);
}
