package com.kuklin.aviabot.processors;

import com.kuklin.aviabot.entities.Flight;
import com.kuklin.aviabot.entities.UserFlight;
import com.kuklin.aviabot.models.FlightDto;
import com.kuklin.aviabot.providers.FlightStatsComFlightInfoProvider;
import com.kuklin.aviabot.services.FlightService;
import com.kuklin.aviabot.services.TelegramService;
import com.kuklin.aviabot.services.UserFlightService;
import com.kuklin.aviabot.telegram.handlers.FlightUpdateHandler;
import com.kuklin.aviabot.telegram.utils.Command;
import com.kuklin.aviabot.telegram.utils.ThreadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FlightSubNotificationScheduleProcessor implements ScheduleProcessor{
    private final UserFlightService userFlightService;
    private final FlightService flightService;
    private final TelegramService telegramService;
    private final FlightStatsComFlightInfoProvider flightInfoProvider;
    @Override
    public void process() {
        List<Flight> flights = flightService.getAllFLight();
        for (Flight flight: flights) {
            //Проверка изменения состояния рейса
            FlightDto info = flightInfoProvider
                    .getFlightInfoOrNull(flight.getFlightCode(), flight.getNumber());
            //Если состояние не изменилось - переходим к следующему рейсу
            if (info == null || flight.equalsDto(info)) {
                continue;
            }
            //Если состояние изменилось - сохраняем новое
            Flight updatedFlight = flightService.updateFlight(flight, info);
            List<UserFlight> userFlights =
                    userFlightService.getUsersFlightByFlightId(updatedFlight.getId());

            for (UserFlight userFlight: userFlights) {
                telegramService.sendReturnedMessage(
                        userFlight.getTelegramId(),
                        flight.getDifference(info),
                        FlightUpdateHandler.getInlineMessageFlight(updatedFlight.getFlightCode(), updatedFlight.getNumber(), Command.UNSUBSCRIBE, Command.UNSUBSCRIBE.getCommandText()),
                        null
                );
                ThreadUtil.sleep(100);
            }
        }

    }

    @Override
    public String getSchedulerName() {
        return this.getClass().getSimpleName();
    }
}
