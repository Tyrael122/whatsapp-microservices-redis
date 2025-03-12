package org.contoso.chatservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChatResponse {

    private String id;

    private String name;

    private List<String> users;
}
