package com.example.universitytask.bot;

import com.example.universitytask.handlers.ResponseHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class UniversityBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botName;
    private final ResponseHandler responseHandler;

    public UniversityBot(@Value("${bot.token}") String botToken, ResponseHandler responseHandler) {
        super(botToken);
        this.responseHandler = responseHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage response = new SendMessage();
        response.setChatId(message.getChatId());
        response.setText(responseHandler.handleResponse(message.getText()));
        try {
            execute(response);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
