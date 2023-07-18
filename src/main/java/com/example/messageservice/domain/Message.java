package com.example.messageservice.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="message")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private int messageId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "email")
    private String email;
    @Column(name = "message")
    private String message;

    @Column(name = "date_created")
    @CreatedDate
    private Timestamp dateCreated;

    @Column(name = "message_status")
    //0 for open, 1 for closed
    private int status;

    @Column(name = "subject")
    private String subject;
}

