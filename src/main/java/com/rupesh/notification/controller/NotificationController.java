package com.rupesh.notification.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupesh.notification.dto.NotificationRequest;
import com.rupesh.notification.service.NotificationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	NotificationService notificationService;
	private static final Logger logger =
	        LoggerFactory.getLogger(NotificationController.class);
	@PostMapping
	ResponseEntity<?> notification(@Valid @RequestBody NotificationRequest request){
		logger.info("Trying to send notification");
		notificationService.send(request);
		
		return ResponseEntity.ok().build();
	}
}