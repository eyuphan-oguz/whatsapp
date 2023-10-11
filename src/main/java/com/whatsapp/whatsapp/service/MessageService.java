package com.whatsapp.whatsapp.service;

import java.util.List;

import com.whatsapp.whatsapp.exception.ChatException;
import com.whatsapp.whatsapp.exception.MessageException;
import com.whatsapp.whatsapp.exception.UserException;
import com.whatsapp.whatsapp.model.Message;
import com.whatsapp.whatsapp.model.User;
import com.whatsapp.whatsapp.request.SendMessageRequest;

public interface MessageService {
    
    public Message sendMessage(SendMessageRequest req) throws UserException, ChatException;

    public List<Message> getChatsMessages(Integer chatId, User reqUser) throws ChatException,UserException;

    public Message findMessageById(Integer messageId) throws MessageException;

    public void deleteMessage(Integer messageId,User reqUser) throws MessageException,UserException;


}
