package com.quikbite.app.email_notification.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.quikbite.app.enums.NotificationType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class NotificationDto {
    Long id;

    private String subject;

    @NotBlank(message = "Recepient is required")
    private String recepient;

    private String body;

    private NotificationType notificationType;
    private LocalDateTime createdAt; // here its not final because in dto we can set it
    private boolean isHtml;
}
