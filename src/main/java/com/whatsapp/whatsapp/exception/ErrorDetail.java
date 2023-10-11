package com.whatsapp.whatsapp.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDetail {
    
    private String error;

    private String message;

    private LocalDateTime timeStamp;

}
