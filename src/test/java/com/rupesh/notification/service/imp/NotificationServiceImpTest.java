package com.rupesh.notification.service.imp;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rupesh.notification.dto.NotificationRequest;
import com.rupesh.notification.entity.NotificationHistory;
import com.rupesh.notification.entity.User;
import com.rupesh.notification.enums.NotificationType;
import com.rupesh.notification.enums.StatusType;
import com.rupesh.notification.repo.NotificationHistoryRepo;
import com.rupesh.notification.service.NotificationProvider;
import com.rupesh.notification.service.UserPreferenceService;
import com.rupesh.notification.service.UserService;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceImpTest {
		@Mock
		private UserService userService;
		@Mock
	    private UserPreferenceService userPreference;

	    @Mock
	    private NotificationHistoryRepo notificationHistoryRepo;

	    @Mock
	    NotificationProvider emailProvider;

	    @Mock
	    NotificationProvider smsProvider;
	    
	    @Mock
	    NotificationProvider inAppProvider;

	    @Mock
	    NotificationProvider pushProvider;
	    
	    private NotificationServiceImp notificationService;

	    @BeforeEach
	    void setUp() {
	        List<NotificationProvider> providers =
	                List.of(emailProvider, smsProvider, inAppProvider, pushProvider);

	        notificationService = new NotificationServiceImp(
	                userPreference, userService, notificationHistoryRepo, providers);
	    }
	
	
	
	    @Test
	    
	    void shouldReturnWhenUserNotFound() {

	       
	        NotificationRequest request = new NotificationRequest();
	        request.setUserID(1);

	        when(userService.getUserById(anyInt()))
	                .thenReturn(null);

	        
	        notificationService.send(request);

	        
	        verify(notificationHistoryRepo, never())
	                .save(any(NotificationHistory.class));

	        verify(emailProvider, never()).send(any());
	        verify(smsProvider, never()).send(any());
	        verify(pushProvider, never()).send(any());
	        verify(inAppProvider, never()).send(any());
	    }
	    
	    
	    @Test
	    
	    void shouldSendEmailWhenEmailIsRequestedAndEnabled() {

	      
	        User user = new User();
	        user.setUserName("Rupesh");

	        NotificationRequest request = new NotificationRequest();
	        request.setUserID(1);
	        request.setChannels(Set.of(NotificationType.EMAIL));

	        when(userService.getUserById(1))
	                .thenReturn(user);

	        when(userPreference.getAllChannel(1))
	                .thenReturn(Set.of(NotificationType.EMAIL));

	        when(emailProvider.getChannel())
	                .thenReturn(NotificationType.EMAIL);

	        when(smsProvider.getChannel())
	                .thenReturn(NotificationType.SMS);

	        when(pushProvider.getChannel())
	                .thenReturn(NotificationType.PUSH);

	        when(inAppProvider.getChannel())
	                .thenReturn(NotificationType.INAPP);

	        when(emailProvider.send(request))
	                .thenReturn(StatusType.SUCCESS);
	       
	        
	        notificationService.send(request);

	        
	        verify(emailProvider).send(request);

	        verify(smsProvider, never()).send(request);
	        verify(pushProvider, never()).send(request);
	        verify(inAppProvider, never()).send(request);

	        verify(notificationHistoryRepo, times(4))
	                .save(any(NotificationHistory.class));
	    }
	    
	    
	    @Test 
	    
	    void shouldNotSendEmailWhenEmailIsNotRequestedAndEnabled() {

	      
	        User user = new User();
	        user.setUserName("Rupesh");

	        NotificationRequest request = new NotificationRequest();
	        request.setUserID(1);
	        request.setChannels(Set.of(NotificationType.SMS ,NotificationType.INAPP));

	        when(userService.getUserById(1))
	                .thenReturn(user);

	        when(userPreference.getAllChannel(1))
	                .thenReturn(Set.of(NotificationType.EMAIL,NotificationType.SMS,NotificationType.INAPP));

	        when(emailProvider.getChannel())
	                .thenReturn(NotificationType.EMAIL);

	        when(smsProvider.getChannel())
	                .thenReturn(NotificationType.SMS);

	        when(pushProvider.getChannel())
	                .thenReturn(NotificationType.PUSH);

	        when(inAppProvider.getChannel())
	                .thenReturn(NotificationType.INAPP);

	        when(inAppProvider.send(request))
            .thenReturn(StatusType.SUCCESS);
	        when(smsProvider.send(request))
            .thenReturn(StatusType.SUCCESS);
	       
	        
	        notificationService.send(request);

	        
	        verify(emailProvider,never()).send(request);

	        verify(smsProvider).send(request);
	        verify(pushProvider, never()).send(request);
	        verify(inAppProvider).send(request);

	        verify(notificationHistoryRepo, times(4))
	                .save(any(NotificationHistory.class));
	    }
	    
	    
	    @Test 
	    void shouldNotSendEmailWhenEmailIsRequestedButNotEnabled() {

	      
	        User user = new User();
	        user.setUserName("Rupesh");

	        NotificationRequest request = new NotificationRequest();
	        request.setUserID(1);
	        request.setChannels(Set.of(NotificationType.EMAIL));

	        when(userService.getUserById(1))
	                .thenReturn(user);

	        when(userPreference.getAllChannel(1))
	                .thenReturn(Set.of(NotificationType.SMS));

	        when(emailProvider.getChannel())
	                .thenReturn(NotificationType.EMAIL);

	        when(smsProvider.getChannel())
	                .thenReturn(NotificationType.SMS);

	        when(pushProvider.getChannel())
	                .thenReturn(NotificationType.PUSH);

	        when(inAppProvider.getChannel())
	                .thenReturn(NotificationType.INAPP);

	        
	        
	        notificationService.send(request);

	        
	        verify(emailProvider,never()).send(request);

	        verify(smsProvider, never()).send(request);
	        verify(pushProvider, never()).send(request);
	        verify(inAppProvider, never()).send(request);

	        verify(notificationHistoryRepo, times(4))
	                .save(any(NotificationHistory.class));
	    }
	    
	    
	    
	    
	    
	    
	    
	    @Test
	  
	    void shouldFailWhenEmailProviderThrowsException() {

	      
	        User user = new User();
	        user.setUserName("Rupesh");

	        NotificationRequest request = new NotificationRequest();
	        request.setUserID(1);
	        request.setChannels(Set.of(NotificationType.EMAIL));

	        when(userService.getUserById(1))
	                .thenReturn(user);

	        when(userPreference.getAllChannel(1))
	                .thenReturn(Set.of(NotificationType.EMAIL));

	        when(emailProvider.getChannel())
	                .thenReturn(NotificationType.EMAIL);

	        when(smsProvider.getChannel())
	                .thenReturn(NotificationType.SMS);

	        when(pushProvider.getChannel())
	                .thenReturn(NotificationType.PUSH);

	        when(inAppProvider.getChannel())
	                .thenReturn(NotificationType.INAPP);
	        

	        when(emailProvider.send(request))
	        .thenThrow(new RuntimeException());
	       
	        
	        notificationService.send(request);

	        
	        verify(emailProvider).send(request);

	        verify(smsProvider, never()).send(request);
	        verify(pushProvider, never()).send(request);
	        verify(inAppProvider, never()).send(request);

	        verify(notificationHistoryRepo, times(4))
	                .save(any(NotificationHistory.class));
	    }
	
}
