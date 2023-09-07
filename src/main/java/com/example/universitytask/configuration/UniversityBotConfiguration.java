package com.example.universitytask.configuration;

import com.example.universitytask.bot.UniversityBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class UniversityBotConfiguration {
    @Bean
    public TelegramBotsApi telegramBotsApi(UniversityBot universityBot) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(universityBot);
        return botsApi;
    }
}
