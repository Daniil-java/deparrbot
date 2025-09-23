package com.kuklin.aviabot.telegram.handlers;

import com.kuklin.aviabot.entities.TelegramUser;
import com.kuklin.aviabot.services.TelegramService;
import com.kuklin.aviabot.services.UserFlightService;
import com.kuklin.aviabot.telegram.utils.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class FlightSubscriptionUpdateHandler implements UpdateHandler {
    private final UserFlightService userFlightService;
    private final TelegramService telegramService;
    private static final String SUBSCRIBE_MSG = "Теперь вы будете получать обновление о рейсе: %s";
    private static final String UNSUBSCRIBE_MSG = "Теперь вы не будете получать обновление о рейсе: %s";
    private static final String ERROR_MSG = "Не получилось обновить статус подписки";
    @Override
    public void handle(Update update, TelegramUser telegramUser) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        String[] messageText = update.getCallbackQuery().getData().trim().split(" ");

        String command = messageText[0];
        String flightCode = messageText[1];
        String number = messageText[2];

        String response = ERROR_MSG;
        if (command.equals(Command.SUBSCRIBE.getCommandText())) {
            userFlightService.subscribeUserToFlight(telegramUser.getTelegramId(), flightCode, number);
            response = SUBSCRIBE_MSG;
        } else if (command.equals(Command.UNSUBSCRIBE.getCommandText())) {
            userFlightService.unsubscribeUserToFlight(telegramUser.getTelegramId(), flightCode, number);
            response = UNSUBSCRIBE_MSG;
        }

        telegramService.sendReturnedMessage(chatId, String.format(response, flightCode + number));
    }

    @Override
    public String getHandlerListName() {
        return Command.SUBSCRIBE.getCommandText();
    }
}
