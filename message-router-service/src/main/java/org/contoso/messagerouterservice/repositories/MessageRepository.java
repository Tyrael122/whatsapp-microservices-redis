package org.contoso.messagerouterservice.repositories;

import org.contoso.messagerouterservice.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findAllByChatId(String chatId);
}
