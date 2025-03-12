package org.contoso.chatservice.controllers;

import lombok.RequiredArgsConstructor;
import org.contoso.chatservice.services.ChatService;
import org.contoso.chatservice.models.dtos.ChatRequest;
import org.contoso.chatservice.models.dtos.ChatResponse;
import org.contoso.chatservice.models.dtos.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponse> createChat(@RequestBody ChatRequest chatRequest) {
        ChatResponse chatResponse = chatService.createChat(chatRequest);
        return ResponseEntity.ok(chatResponse);
    }

    @PostMapping("/{chatId}/users")
    public ResponseEntity<Void> addUserToChat(@PathVariable String chatId, @RequestBody String userId) {
        chatService.addUserToChat(chatId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> getChats() {
        List<ChatResponse> chats = chatService.getChats();
        return ResponseEntity.ok(chats);
    }

    @GetMapping("/{chatId}/users")
    public ResponseEntity<List<UserResponse>> getChatUsers(@PathVariable String chatId) {
        List<UserResponse> users = chatService.getChatUsers(chatId);
        return ResponseEntity.ok(users);
    }
}