package com.kuklin.aviabot.providers;

import com.kuklin.aviabot.models.BoardRequest;
import com.kuklin.aviabot.models.flightInfo.FlightInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface DepartureBoardProvider {
    List<FlightInfo> getBoard(BoardRequest boardRequest);
}
