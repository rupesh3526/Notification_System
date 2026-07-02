package com.rupesh.notification.service.imp;

import com.rupesh.notification.dto.NotificationRequest;
import com.rupesh.notification.enums.NotificationType;
import com.rupesh.notification.enums.StatusType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rupesh.notification.service.NotificationProvider;
@Service
public class PushProvider implements NotificationProvider {

	private static final Logger logger =
            LoggerFactory.getLogger(PushProvider.class);
	
	@Override
	public NotificationType getChannel() {
		
		return NotificationType.PUSH;
	}

	@Override
	public StatusType send(NotificationRequest req) {
		logger.info("Sending Push Notification");
		return StatusType.SUCCESS;
	}

}
