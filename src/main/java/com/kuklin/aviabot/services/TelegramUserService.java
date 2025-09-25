package com.kuklin.aviabot.services;

import com.kuklin.aviabot.entities.TelegramUser;
import com.kuklin.aviabot.repository.TelegramUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class TelegramUserService {
    private final TelegramUserRepository telegramUserRepository;

    public TelegramUser getTelegramUserByIdOrNull(Long telegramId) {
        return telegramUserRepository.findById(telegramId).orElse(null);
    }

    public TelegramUser createOrGetUserByTelegram(User telegramUser) {
        Optional<TelegramUser> optionalTelegramUser =
                telegramUserRepository.findById(telegramUser.getId());

        if (optionalTelegramUser.isPresent()) {
            return optionalTelegramUser.get();
        }
        TelegramUser tgUser = TelegramUser.convertFromTelegram(telegramUser);
        return telegramUserRepository.save(tgUser);
    }
}
