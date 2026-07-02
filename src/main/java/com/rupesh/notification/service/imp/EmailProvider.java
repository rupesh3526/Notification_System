package com.rupesh.notification.service.imp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rupesh.notification.dto.NotificationRequest;
import com.rupesh.notification.enums.NotificationType;
import com.rupesh.notification.enums.StatusType;
import com.rupesh.notification.service.NotificationProvider;
@Service
public class EmailProvider implements NotificationProvider {

	private static final Logger logger =
            LoggerFactory.getLogger(EmailProvider.class);
	
	@Override
	public NotificationType getChannel() {
		
		return NotificationType.EMAIL;
	}

	@Override
	public StatusType send(NotificationRequest req) {
		logger.info("Sending Email Notification");
		return StatusType.SUCCESS ;
	}

}
