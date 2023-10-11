package com.whatsapp.whatsapp.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthResponse {
    private String jwt;
    private boolean isAuth;
}
