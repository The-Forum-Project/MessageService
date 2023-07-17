package com.example.messageservice.controller;

import com.example.messageservice.domain.Message;
import com.example.messageservice.dto.common.GeneralResponse;
import com.example.messageservice.dto.common.AllMessageResponse;
import com.example.messageservice.dto.common.MessageResponse;
import com.example.messageservice.exception.MessageNotFoundException;
import com.example.messageservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/messages")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    //TODO: 1. 增加token authentication 里面去获取userId
    //根据token来判断 userId，如果没有userId，就给一个-1 作为userId 表示是visitor
    @PostMapping
    public ResponseEntity<GeneralResponse> createMessage(@RequestBody Message message) {
        messageService.createMessage(message);
        return ResponseEntity.ok(GeneralResponse.builder().statusCode("200").message("Message created.").build());
    }

    @GetMapping
    public ResponseEntity<AllMessageResponse> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(AllMessageResponse.builder().statusCode("200").messages(messages).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getMessage(@PathVariable int id) throws MessageNotFoundException {
        Message message = messageService.getMessage(id);
        return ResponseEntity.ok(MessageResponse.builder().statusCode("200").message(message).build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateStatus(@PathVariable int id) throws MessageNotFoundException {
        boolean result = messageService.updateStatus(id);
        if(result){
            return ResponseEntity.ok(GeneralResponse.builder().statusCode("200").message("Message updated.").build());
        }else{
            return ResponseEntity.ok(GeneralResponse.builder().statusCode("400").message("Message already closed.").build());
        }
    }

}
