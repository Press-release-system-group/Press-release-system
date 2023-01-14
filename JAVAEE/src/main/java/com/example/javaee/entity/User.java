package com.example.javaee.entity;

import com.example.javaee.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int user_id;
    private String username;
    private String password;
    private Role role;
    private String email;
    private String phone;
    private String name;
}

