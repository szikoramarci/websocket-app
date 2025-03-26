package com.webness.websocket_app.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recording {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32, nullable = false, unique = true)
    private String publicId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false)
    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RecordingStatus status;

    @Column(nullable = false, length = 1000)
    private String sedation;

    @Column(nullable = false, length = 1000)
    private String activation;

    @Column(nullable = false, length = 1000)
    private String medication;

    @PrePersist
    public void generatePublicId() {
        if (publicId == null) {
            this.publicId = UUID.randomUUID().toString().replace("-", "");
        }
    }
}
