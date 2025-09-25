package com.kuklin.aviabot.repository;

import com.kuklin.aviabot.entities.UserFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFlightRepository extends JpaRepository<UserFlight, Long> {
    List<UserFlight> findByTelegramId(Long telegramId);
    List<UserFlight> findByFlightId(Long flightId);
    Boolean existsUserFlightByFlightIdAndTelegramId(Long flightId, Long telegramId);
    List<UserFlight> findAllByFlightIdAndTelegramId(Long flightId, Long telegramId);

}
