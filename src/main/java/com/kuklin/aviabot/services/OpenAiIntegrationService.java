package com.kuklin.aviabot.services;

import com.kuklin.aviabot.integrations.OpenAiFeignClient;
import com.kuklin.aviabot.models.openaimodels.OpenAiChatCompletionRequest;
import com.kuklin.aviabot.models.openaimodels.OpenAiChatCompletionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OpenAiIntegrationService {

    private final OpenAiFeignClient openAiFeignClient;
    private final String aiKey;

    public OpenAiIntegrationService(@Value("${GENERATION_TOKEN}") String aiKey, OpenAiFeignClient openAiFeignClient) {
        this.aiKey = aiKey;
        this.openAiFeignClient = openAiFeignClient;
    }

    public String fetchResponse(String content) {
        OpenAiChatCompletionRequest request =
                OpenAiChatCompletionRequest.makeDefaultRequest(content);

        OpenAiChatCompletionResponse response =
                openAiFeignClient.generate("Bearer " + aiKey, request);

        return response.getChoices().get(0).toString();
    }
}
