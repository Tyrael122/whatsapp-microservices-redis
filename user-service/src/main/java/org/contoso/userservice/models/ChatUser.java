package org.contoso.userservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class ChatUser {

    @Id
    private String id;

    private String name;
}
