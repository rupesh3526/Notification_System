package com.rupesh.notification.service;

import java.util.Set;

import com.rupesh.notification.enums.NotificationType;

public interface UserPreferenceService {
	Set<NotificationType> getAllChannel(int userId);

}
