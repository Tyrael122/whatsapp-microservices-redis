package org.contoso.userservice.repositories;

import org.contoso.userservice.models.ChatUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<ChatUser, String> {
}
