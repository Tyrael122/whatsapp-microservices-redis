package org.contoso.messagerouterservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class ChatMessage {

    @Id
    private String id;

    @Indexed
    private String chatId;

    @Indexed
    private LocalDateTime timestamp;

    private String sender;

    private String content;
}
