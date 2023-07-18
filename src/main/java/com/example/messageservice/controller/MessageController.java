package com.example.messageservice.controller;

import com.example.messageservice.domain.Message;
import com.example.messageservice.dto.common.GeneralResponse;
import com.example.messageservice.dto.common.AllMessageResponse;
import com.example.messageservice.dto.common.MessageResponse;
import com.example.messageservice.exception.InvalidAuthorityException;
import com.example.messageservice.exception.MessageNotFoundException;
import com.example.messageservice.security.AuthUserDetail;
import com.example.messageservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @PostMapping
    public ResponseEntity<GeneralResponse> createMessage(@RequestBody Message message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = authentication == null ? 0l : (Long) authentication.getPrincipal();
        message.setUserId(userId);
        messageService.createMessage(message);
        return ResponseEntity.ok(GeneralResponse.builder().statusCode("200").message("Message created.").build());
    }

    @GetMapping
    public ResponseEntity<AllMessageResponse> getAllMessages() throws InvalidAuthorityException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        if (authorities.stream().noneMatch(authority -> authority.getAuthority().equals("admin"))){
            throw new InvalidAuthorityException();
        }else{
            List<Message> messages = messageService.getAllMessages();
            return ResponseEntity.ok(AllMessageResponse.builder().statusCode("200").messages(messages).build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getMessage(@PathVariable int id) throws MessageNotFoundException, InvalidAuthorityException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        if (authorities.stream().noneMatch(authority -> authority.getAuthority().equals("admin"))){
            throw new InvalidAuthorityException();
        }else{
            Message message = messageService.getMessage(id);
            return ResponseEntity.ok(MessageResponse.builder().statusCode("200").message(message).build());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateStatus(@PathVariable int id) throws MessageNotFoundException, InvalidAuthorityException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        if (authorities.stream().noneMatch(authority -> authority.getAuthority().equals("admin"))){
            throw new InvalidAuthorityException();
        }else{

            boolean result = messageService.updateStatus(id);
            if(result){
                return ResponseEntity.ok(GeneralResponse.builder().statusCode("200").message("Message updated.").build());
            }else{
                return ResponseEntity.ok(GeneralResponse.builder().statusCode("400").message("Message already closed.").build());
            }
        }
    }

}
