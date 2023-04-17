package com.auth.jwt.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionDetails {
    private String message;
    private String title;
    private int status;
    private LocalDateTime timeStamp;
}
