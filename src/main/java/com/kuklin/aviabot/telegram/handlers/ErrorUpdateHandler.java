package com.kuklin.aviabot.telegram.handlers;

import com.kuklin.aviabot.entities.TelegramUser;
import com.kuklin.aviabot.services.TelegramService;
import com.kuklin.aviabot.telegram.utils.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

//Обработчик непродусмотренных сообщений
@Component
@RequiredArgsConstructor
public class ErrorUpdateHandler implements UpdateHandler {
    private static final String RESPONSE = "Данный запрос непредусмотрен сервисом.";
    private final TelegramService telegramService;
    @Override
    public void handle(Update update, TelegramUser telegramUser) {
        long chatId = update.hasCallbackQuery() ?
                update.getCallbackQuery().getMessage().getChatId() :
                update.getMessage().getChatId();

        telegramService.sendReturnedMessage(
                chatId,
                RESPONSE
        );
    }

    @Override
    public String getHandlerListName() {
        return Command.ERROR.getCommandText();
    }
}
