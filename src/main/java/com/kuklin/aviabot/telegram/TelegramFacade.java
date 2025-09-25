package com.kuklin.aviabot.telegram;

import com.kuklin.aviabot.entities.TelegramUser;
import com.kuklin.aviabot.services.TelegramUserService;
import com.kuklin.aviabot.telegram.handlers.UpdateHandler;
import com.kuklin.aviabot.telegram.utils.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class TelegramFacade {
    private Map<String, UpdateHandler> updateHandlerMap = new ConcurrentHashMap<>();
    @Autowired
    private TelegramUserService telegramUserService;

    public void register(String command, UpdateHandler updateHandler) {
        if (updateHandlerMap.containsKey(command)) {
            log.error("This command is already exists!");
        }
        updateHandlerMap.put(command, updateHandler);
    }

    public void handleUpdate(Update update) {
        if (!update.hasCallbackQuery() && !update.hasMessage()) return;
        User user = update.getMessage() != null ?
                update.getMessage().getFrom() :
                update.getCallbackQuery().getFrom();

        TelegramUser telegramUser = telegramUserService.createOrGetUserByTelegram(user);

        processInputUpdate(update).handle(update, telegramUser);
    }

    public UpdateHandler processInputUpdate(Update update) {
        String request;
        if (update.hasCallbackQuery()) {
            return updateHandlerMap.get(Command.SUBSCRIBE.getCommandText());
        } else {
            request = update.getMessage().getText().split(" ")[0];
        }

        UpdateHandler updateHandler = updateHandlerMap.get(request);
        if (updateHandler == null) {
            return updateHandlerMap.get(Command.ERROR.getCommandText());
        }
        return updateHandler;

    }
}
