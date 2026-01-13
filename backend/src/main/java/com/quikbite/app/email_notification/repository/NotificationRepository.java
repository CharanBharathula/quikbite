package com.quikbite.app.email_notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quikbite.app.email_notification.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
