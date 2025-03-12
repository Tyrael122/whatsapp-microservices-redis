package org.contoso.chatservice.services;

import lombok.RequiredArgsConstructor;
import org.contoso.chatservice.models.dtos.ChatRequest;
import org.contoso.chatservice.models.dtos.ChatResponse;
import org.contoso.chatservice.models.dtos.UserResponse;
import org.contoso.chatservice.models.Chat;
import org.contoso.chatservice.repositories.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public ChatResponse createChat(ChatRequest chatRequest) {
        Chat chat = new Chat();
        chat.setName(chatRequest.getName());
        chat.setUsers(chatRequest.getUsers());
        chat = chatRepository.save(chat);
        return new ChatResponse(chat.getId(), chat.getName(), chat.getUsers());
    }

    public void addUserToChat(String chatId, String userId) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found"));
        chat.getUsers().add(userId);
        chatRepository.save(chat);
    }

    public List<ChatResponse> getChats() {
        return chatRepository.findAll().stream()
                .map(chat -> new ChatResponse(chat.getId(), chat.getName(), chat.getUsers()))
                .collect(Collectors.toList());
    }

    public List<UserResponse> getChatUsers(String chatId) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found"));
        return chat.getUsers().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }
}