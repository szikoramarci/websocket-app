package com.webness.websocket_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webness.websocket_app.entity.Recording;
import java.util.Optional;

@Repository
public interface RecordingRepository extends JpaRepository<Recording, Long> {

    Optional<Recording> findByPublicId(String publicId);
}
