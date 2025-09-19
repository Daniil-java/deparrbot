package com.kuklin.aviabot.models.openaimodels;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatRole {
    USER("user"), ASSISTANT("assistant"), MODEL("model");

    private String value;
}
