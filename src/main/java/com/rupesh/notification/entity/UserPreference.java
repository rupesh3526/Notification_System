package com.rupesh.notification.entity;

import com.rupesh.notification.enums.NotificationType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class UserPreference {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
int preferencesId;
@ManyToOne
@JoinColumn(name = "user_id")
User user;
@Enumerated(EnumType.STRING)
NotificationType channel;

}
