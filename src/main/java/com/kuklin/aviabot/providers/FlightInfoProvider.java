package com.kuklin.aviabot.providers;

import com.kuklin.aviabot.models.FlightDto;

public interface FlightInfoProvider {
    FlightDto getFlightInfoOrNull(String flight, String number);

    String getFlightInfoProviderOrigin();
}
