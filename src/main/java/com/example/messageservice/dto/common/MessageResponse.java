package com.example.messageservice.dto.common;

import com.example.messageservice.domain.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageResponse {
    private String statusCode;
    private Message message;
}
