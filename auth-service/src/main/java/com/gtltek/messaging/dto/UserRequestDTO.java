package com.gtltek.messaging.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDTO {
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
}