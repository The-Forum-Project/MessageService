package com.example.messageservice.exception;

public class MessageNotFoundException extends Exception{

    public MessageNotFoundException() {
        super("Message is not found");
    }

}
