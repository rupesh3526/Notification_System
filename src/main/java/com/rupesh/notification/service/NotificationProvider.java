package com.rupesh.notification.service;

import com.rupesh.notification.dto.NotificationRequest;
import com.rupesh.notification.enums.NotificationType;
import com.rupesh.notification.enums.StatusType;

public interface NotificationProvider { 
	NotificationType getChannel();
	StatusType send( NotificationRequest req);
}
