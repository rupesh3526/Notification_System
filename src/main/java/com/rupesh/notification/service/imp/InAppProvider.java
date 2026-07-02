package com.rupesh.notification.service.imp;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rupesh.notification.dto.NotificationRequest;
import com.rupesh.notification.enums.NotificationType;
import com.rupesh.notification.enums.StatusType;
import com.rupesh.notification.service.NotificationProvider;
@Service
public class InAppProvider implements NotificationProvider {

	private static final Logger logger =
            LoggerFactory.getLogger(InAppProvider.class);
	@Override
	public StatusType send( NotificationRequest req) {
		logger.info("Sending InApp Notification");
		return StatusType.SUCCESS;
		
	}

	@Override
	public NotificationType getChannel() {
		
		return NotificationType.INAPP;
	}

}
