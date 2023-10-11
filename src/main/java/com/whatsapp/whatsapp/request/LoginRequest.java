package com.whatsapp.whatsapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequest {

    private String email; 
    private String password;
    
}
