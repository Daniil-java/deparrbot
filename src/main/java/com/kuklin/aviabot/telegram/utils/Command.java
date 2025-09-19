package com.kuklin.aviabot.telegram.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Command {
    START("/start"),
    FLIGHT("/flight"),
    BOARD("/board")
    ;

    private final String commandText;
}
