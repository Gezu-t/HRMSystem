package com.gtltek.client;


import com.gtltek.messaging.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/api/users/username/{username}")
    UserResponseDTO getUserByUsername(@PathVariable("username") String username);  // Fetch by username
}
