package com.example.messageservice.dto.common;

import com.example.messageservice.domain.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AllMessageResponse {

    private String statusCode;
    private List<Message> messages;
}
