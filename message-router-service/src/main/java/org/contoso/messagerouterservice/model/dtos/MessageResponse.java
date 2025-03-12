package org.contoso.messagerouterservice.model.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MessageResponse implements Serializable {
    private String id;

    private String chatId;
    private LocalDateTime timestamp;

    private String sender;
    private String content;
}
