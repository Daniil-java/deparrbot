package com.kuklin.aviabot.telegram.handlers;

import com.kuklin.aviabot.models.BoardRequest;
import com.kuklin.aviabot.models.TelegramUser;
import com.kuklin.aviabot.models.flightInfo.FlightInfo;
import com.kuklin.aviabot.providers.DepartureBoardProvider;
import com.kuklin.aviabot.services.TelegramService;
import com.kuklin.aviabot.telegram.utils.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BordUpdateHandler implements UpdateHandler{
    private final TelegramService telegramService;
    private final DepartureBoardProvider departureBoardProvider;
    @Override
    public void handle(Update update, TelegramUser telegramUser) {
        long chatId = update.getMessage().getChatId();
        //Ожидается сообщения типа "/board Бангкок -> Хошимин, 2025-09-20"
        String messageText = update.getMessage().getText();

        BoardRequest boardRequest = extractBoardRequest(messageText);
        List<FlightInfo> flightInfos = departureBoardProvider.getBoard(boardRequest);

        telegramService.sendReturnedMessage(chatId, FlightInfo.getFlightInfoListText(flightInfos));
    }

    private BoardRequest extractBoardRequest(String input) {
        // 1. Убираем команду и лишние пробелы
        String payload = input.substring(input.indexOf(' ') + 1).trim();

        // 2. Находим разделитель маршрута
        int arrowIndex = payload.indexOf("->");
        String fromCity = payload.substring(0, arrowIndex).trim();

        // 3. Оставшаяся часть после "->"
        String afterArrow = payload.substring(arrowIndex + 2).trim();

        // 4. Находим запятую перед датой
        int commaIndex = afterArrow.indexOf(',');
        String toCity = afterArrow.substring(0, commaIndex).trim();

        // 5. Дата — всё, что после запятой
        String date = afterArrow.substring(commaIndex + 1).trim();

        return new BoardRequest()
                .setDeparture(fromCity)
                .setArrival(toCity)
                .setDate(LocalDateTime.parse(date));
    }

    @Override
    public String getHandlerListName() {
        return Command.BOARD.getCommandText();
    }
}
