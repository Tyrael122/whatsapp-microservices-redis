package org.contoso.messagerouterservice.model.dtos;

import lombok.Data;
import org.contoso.messagerouterservice.model.ChatMessage;

import java.time.LocalDateTime;

@Data
public class MessageRequest {
    private String sender;
    private String content;

    public ChatMessage toChatMessage(String chatId) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setChatId(chatId);
        chatMessage.setTimestamp(LocalDateTime.now());

        chatMessage.setSender(sender);
        chatMessage.setContent(content);
        return chatMessage;
    }
}
