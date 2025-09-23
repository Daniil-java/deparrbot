package com.kuklin.aviabot.providers;

import com.kuklin.aviabot.models.BoardRequest;
import com.kuklin.aviabot.models.FlightDto;

import java.util.List;

public interface DepartureBoardProvider {
    List<FlightDto> getBoard(BoardRequest boardRequest);
}
