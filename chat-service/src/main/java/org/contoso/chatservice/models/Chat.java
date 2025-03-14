package org.contoso.chatservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Chat {


    @Id
    private String id;

    private String name;

    private List<String> users;
}
