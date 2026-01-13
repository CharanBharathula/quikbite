package com.quikbite.app.email_notification.model;

import java.time.LocalDateTime;

import com.quikbite.app.enums.NotificationType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "notifications")
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this is used to auto-generate the primary key value
    Long id;

    private String subject;

    @NotBlank(message = "Recepient is required")
    private String recepient;

    @Lob // @Lob is used to store large objects in the database, such as large text or binary data.
    private String body;

    @Enumerated( EnumType.STRING ) // to store the enum as a string in the database
    private NotificationType notificationType;
    private final LocalDateTime createdAt = LocalDateTime.now(); // its final because it should be set only once at creation time
    private boolean isHtml;
}
