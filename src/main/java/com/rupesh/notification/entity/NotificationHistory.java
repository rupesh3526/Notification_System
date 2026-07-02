 package com.rupesh.notification.entity;

import java.time.LocalDateTime;

import com.rupesh.notification.enums.NotificationType;
import com.rupesh.notification.enums.StatusType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class NotificationHistory {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
@Enumerated(EnumType.STRING)
private NotificationType notificationChannel;
@Enumerated(EnumType.STRING)
private StatusType status;
private LocalDateTime timestamp  ;
@PrePersist
protected void onCreate() {
    this.timestamp = LocalDateTime.now();

}
}