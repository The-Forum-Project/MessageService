package com.example.messageservice.service;

import com.example.messageservice.domain.Message;
import com.example.messageservice.exception.MessageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.messageservice.dao.MessageDao;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class MessageService {

    private MessageDao messageDao;
    @Autowired
    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void createMessage(Message message) {
        Timestamp timestamp = Timestamp.from(Instant.now());
        message.setDateCreated(timestamp);
        messageDao.createMessage(message);
    }


    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }

    public Message getMessage(int id) throws MessageNotFoundException {
        return messageDao.getMessage(id);
    }

    public boolean updateStatus(int id) throws MessageNotFoundException {
        Message message = messageDao.getMessage(id);

        if (message != null && message.getStatus() == 0) {
            message.setStatus(1);
            messageDao.updateMessage(message);
            return true;
        } else if (message != null && message.getStatus() == 1) {
            return false;
        } else {
            throw new MessageNotFoundException();
        }
    }
}
