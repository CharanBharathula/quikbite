package com.quikbite.app.email_notification.service;

import com.quikbite.app.email_notification.dtos.NotificationDto;

public interface NotificationService {
    void sendEmail( NotificationDto notificationDto );
}
