package com.rupesh.notification.service.imp;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.rupesh.notification.enums.NotificationType;
import com.rupesh.notification.repo.UserPreferenceRepo;
import com.rupesh.notification.service.UserPreferenceService;
@Service
public class UserPreferenceImp implements UserPreferenceService  {
	
	UserPreferenceRepo userPreferenceRepo;
	UserPreferenceImp( UserPreferenceRepo userPreferenceRepo){
		this.userPreferenceRepo=userPreferenceRepo;
	}
	@Override
	public Set<NotificationType> getAllChannel(int userId) {
	Set<NotificationType> preference=	userPreferenceRepo.findChannelsByUserId(userId);
	
				return preference;
	}

}
