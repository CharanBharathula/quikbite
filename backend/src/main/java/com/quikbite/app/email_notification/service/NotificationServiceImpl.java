package com.quikbite.app.email_notification.service;

import java.nio.charset.StandardCharsets;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.quikbite.app.email_notification.dtos.NotificationDto;
import com.quikbite.app.email_notification.model.Notification;
import com.quikbite.app.email_notification.repository.NotificationRepository;
import com.quikbite.app.enums.NotificationType;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final JavaMailSender mailSender;
    private final NotificationRepository notificationRepository;

    @Override
    @Async
    public void sendEmail( NotificationDto notificationDto ){
        log.info("Sending email to {}", notificationDto.getRecepient());
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
            );
            helper.setTo(notificationDto.getRecepient());
            helper.setSubject(notificationDto.getSubject());
            helper.setText(notificationDto.getBody(), true);
            mailSender.send(mimeMessage);

            // save to database
            Notification notification = Notification.builder()
                .recepient(notificationDto.getRecepient())
                .subject(notificationDto.getSubject())
                .body(notificationDto.getBody())
                .notificationType(NotificationType.EMAIL)
                .isHtml(notificationDto.isHtml())
                .build();
            notificationRepository.save(notification);
            log.info("Saved to notification table");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
