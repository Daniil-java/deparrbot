package com.kuklin.aviabot.telegram.handlers;

import com.kuklin.aviabot.models.TelegramUser;
import com.kuklin.aviabot.models.flightInfo.FlightInfo;
import com.kuklin.aviabot.providers.FlightInfoProvider;
import com.kuklin.aviabot.services.TelegramService;
import com.kuklin.aviabot.telegram.utils.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class FlightUpdateHandler implements UpdateHandler{
    private final TelegramService telegramService;
    private final FlightInfoProvider flightInfoProvider;
    @Override
    public void handle(Update update, TelegramUser telegramUser) {
        //Ожидается команда, типа "/start SU 123"
        long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();

        String[] flightNumber = messageText.trim().split(" ");
        if (flightNumber.length < 3) {
            //TODO ERROR
        }
        String flight = flightNumber[1];
        String number = flightNumber[2];


        FlightInfo flightInfo = flightInfoProvider.getFlightInfoOrNull(flight, number);

        telegramService.sendReturnedMessage(chatId, flightInfo.getFlightInfoText());

    }

    @Override
    public String getHandlerListName() {
        return Command.FLIGHT.getCommandText();
    }
}
