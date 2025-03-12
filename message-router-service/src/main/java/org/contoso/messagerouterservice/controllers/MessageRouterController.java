package org.contoso.messagerouterservice.controllers;

import lombok.RequiredArgsConstructor;
import org.contoso.messagerouterservice.model.dtos.MessageRequest;
import org.contoso.messagerouterservice.model.dtos.MessageResponse;
import org.contoso.messagerouterservice.services.MessageRouterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message-router")
@RequiredArgsConstructor
public class MessageRouterController {

    private final MessageRouterService messageRouterService;

    @PostMapping("/chat/{chatId}/send")
    public void routeMessage(@PathVariable("chatId") String chatId, @RequestBody MessageRequest message) {
        messageRouterService.routeMessage(chatId, message);
    }

    @GetMapping("/chat/{chatId}/messages")
    public List<MessageResponse> getMessages(@PathVariable("chatId") String chatId) {
        return messageRouterService.getMessages(chatId);
    }
}
