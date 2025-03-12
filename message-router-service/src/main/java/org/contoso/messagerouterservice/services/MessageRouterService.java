package org.contoso.messagerouterservice.services;

import lombok.RequiredArgsConstructor;
import org.contoso.messagerouterservice.model.ChatMessage;
import org.contoso.messagerouterservice.model.dtos.MessageRequest;
import org.contoso.messagerouterservice.model.dtos.MessageResponse;
import org.contoso.messagerouterservice.repositories.MessageRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageRouterService {
    private final MessageRepository messageRepository;

    @CacheEvict(value = "messages", key = "#chatId")
    public void routeMessage(String chatId, MessageRequest message) {
        validateMessageRequest(message);

        messageRepository.save(convertMessageRequestToChatMessage(chatId, message));
    }

    @Cacheable(value = "messages", key = "#chatId")
    public List<MessageResponse> getMessages(String chatId) {
        return messageRepository.findAllByChatId(chatId).stream().map(this::convertChatMessageToMessageResponse).toList();
    }

    private MessageResponse convertChatMessageToMessageResponse(ChatMessage chatMessage) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setId(chatMessage.getId());
        messageResponse.setChatId(chatMessage.getChatId());
        messageResponse.setTimestamp(chatMessage.getTimestamp());
        messageResponse.setSender(chatMessage.getSender());
        messageResponse.setContent(chatMessage.getContent());
        return messageResponse;
    }

    private void validateMessageRequest(MessageRequest message) {
        if (message.getSender() == null || message.getContent() == null) {
            throw new IllegalArgumentException("Sender and content must be provided");
        }

        if (message.getSender().isBlank() || message.getContent().isBlank()) {
            throw new IllegalArgumentException("Sender and content must not be empty");
        }
    }

    private ChatMessage convertMessageRequestToChatMessage(String chatId, MessageRequest messageRequest) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setChatId(chatId);
        chatMessage.setTimestamp(LocalDateTime.now());

        chatMessage.setSender(messageRequest.getSender());
        chatMessage.setContent(messageRequest.getContent());
        return chatMessage;
    }
}
