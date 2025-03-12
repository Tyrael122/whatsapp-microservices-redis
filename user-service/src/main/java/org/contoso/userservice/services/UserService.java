package org.contoso.userservice.services;

import lombok.RequiredArgsConstructor;
import org.contoso.userservice.models.ChatUser;
import org.contoso.userservice.models.dtos.UserRequest;
import org.contoso.userservice.models.dtos.UserResponse;
import org.contoso.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest userRequest) {
        ChatUser user = new ChatUser();
        user.setName(userRequest.getName());
        ChatUser savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getName());
    }

    public UserResponse getUserById(String id) {
        ChatUser user = userRepository.findById(id).orElseThrow();
        return new UserResponse(user.getId(), user.getName());
    }
}
