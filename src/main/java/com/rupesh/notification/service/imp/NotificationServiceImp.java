package com.rupesh.notification.service.imp;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rupesh.notification.dto.NotificationRequest;
import com.rupesh.notification.entity.NotificationHistory;
import com.rupesh.notification.entity.User;
import com.rupesh.notification.enums.NotificationType;
import com.rupesh.notification.enums.StatusType;
import com.rupesh.notification.repo.NotificationHistoryRepo;
import com.rupesh.notification.service.NotificationProvider;
import com.rupesh.notification.service.NotificationService;
import com.rupesh.notification.service.UserPreferenceService;
import com.rupesh.notification.service.UserService;
@Service
public class NotificationServiceImp implements NotificationService {
	@Autowired
	List< NotificationProvider> notificationProvider ;
	@Autowired
	NotificationHistoryRepo  notificationHistoryRepo;
	@Autowired
	UserPreferenceService userPreference;
	@Autowired
	UserService userService;
	private static final Logger logger =
            LoggerFactory.getLogger(NotificationServiceImp.class);

	
	@Override
	public void send(NotificationRequest nr) {
		User user =userService.getUserById(nr.getUserID());
		if(user==null) {
			logger.info("User with id {} not present",nr.getUserID());
			return;
		}
		Set<NotificationType> preference = userPreference.getAllChannel(nr.getUserID());
		
		for(NotificationProvider notificationProvider : notificationProvider) {
			NotificationHistory notificationHistory = new NotificationHistory();
			notificationHistory.setNotificationChannel(notificationProvider.getChannel());
			notificationHistory.setUser(user);
			try {
			if(nr.getChannels().contains(notificationProvider.getChannel())&& preference.contains(notificationProvider.getChannel())) {
			StatusType status=	notificationProvider.send( nr);
				
				notificationHistory.setStatus(status);
				notificationHistoryRepo.save(notificationHistory);
				logger.info("Notification for user {} sent via {} at{}  is {}",user.getUserName(),notificationProvider.getChannel(),notificationHistory.getTimestamp()
						,status);
				
			}
			else {
				
				notificationHistory.setStatus(StatusType.SKIPPED);
				notificationHistoryRepo.save(notificationHistory);
				logger.info("Notification for user {}  via {} at {} is {}",user.getUserName(),notificationProvider.getChannel(),notificationHistory.getTimestamp() ,StatusType.SKIPPED);
			
			}}
			catch (Exception e) {
				notificationHistory.setStatus(StatusType.FAILED);
				notificationHistoryRepo.save(notificationHistory);
				logger.info("Notification for user {}  via {} at {} is {}",user.getUserName(),notificationProvider.getChannel(),notificationHistory.getTimestamp() ,StatusType.FAILED);
			}
			
		}
	
		}
	
	
	

}
