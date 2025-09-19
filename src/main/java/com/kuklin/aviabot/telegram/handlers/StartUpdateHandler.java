package com.kuklin.aviabot.telegram.handlers;

import com.kuklin.aviabot.models.TelegramUser;
import com.kuklin.aviabot.services.TelegramService;
import com.kuklin.aviabot.telegram.utils.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class StartUpdateHandler implements UpdateHandler {

    private static final String START_MESSAGE =
            """
                    /flight SU 123 - напиши в таком формате, чтобы получить информацию по рейсу.
                    /board Москва -> Хошимин, ГГГГ-ММ-ДД - чтобы получить расписание
                    """;
    private final TelegramService telegramService;

    @Override
    public void handle(Update update, TelegramUser telegramUser) {
        telegramService.sendReturnedMessage(update.getMessage().getChatId(), START_MESSAGE);
    }

    @Override
    public String getHandlerListName() {
        return Command.START.getCommandText();
    }
}
