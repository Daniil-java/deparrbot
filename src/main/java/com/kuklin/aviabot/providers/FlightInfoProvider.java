package com.kuklin.aviabot.providers;

import com.kuklin.aviabot.models.flightInfo.FlightInfo;

public interface FlightInfoProvider {
    FlightInfo getFlightInfoOrNull(String flight, String number);

    String getFlightInfoProviderOrigin();
}
