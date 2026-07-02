package com.rupesh.notification.dto;

import java.util.Set;

import com.rupesh.notification.enums.NotificationType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {
	@NotNull
	int userID;
	@NotNull
	String notificationTittle;
	String notificationMessage;
	@NotNull
	Set<NotificationType> channels; 
	
	
}
