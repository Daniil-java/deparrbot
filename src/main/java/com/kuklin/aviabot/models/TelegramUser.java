package com.kuklin.aviabot.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.telegram.telegrambots.meta.api.objects.User;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TelegramUser {
    private Long telegramId;
    private String username;
    private String firstname;
    private String lastname;
    private String languageCode;


    public static TelegramUser convertFromTelegram(User user) {
        return new TelegramUser()
                .setTelegramId(user.getId())
                .setUsername(user.getUserName())
                .setFirstname(user.getFirstName())
                .setLastname(user.getLastName())
                .setLanguageCode(user.getLanguageCode());
    }
}
