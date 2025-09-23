package com.kuklin.aviabot.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_flights")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UserFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long telegramId;

    @Column(nullable = false)
    private Long flightId;

    @CreationTimestamp
    private LocalDateTime subscribedAt;
    private boolean notificationsEnabled;


}
