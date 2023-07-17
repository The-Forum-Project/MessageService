package com.example.messageservice.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GeneralResponse {
    private String statusCode;
    private String message;
}
