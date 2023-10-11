package com.whatsapp.whatsapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whatsapp.whatsapp.exception.ChatException;
import com.whatsapp.whatsapp.exception.MessageException;
import com.whatsapp.whatsapp.exception.UserException;
import com.whatsapp.whatsapp.model.Chat;
import com.whatsapp.whatsapp.model.Message;
import com.whatsapp.whatsapp.model.User;
import com.whatsapp.whatsapp.repository.ChatRepository;
import com.whatsapp.whatsapp.repository.MessageRepository;
import com.whatsapp.whatsapp.request.SendMessageRequest;

@Service
public class MessageServiceImplementation implements MessageService{

    private MessageRepository messageRepository;
    private UserService userService;
    private ChatService chatService;

    
    @Autowired
    public MessageServiceImplementation(MessageRepository messageRepository, UserService userService,
            ChatService chatService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.chatService = chatService;
    }

    @Override
    public Message sendMessage(SendMessageRequest req) throws UserException, ChatException {
        User user = userService.findByUserId(req.getUserId());
        Chat chat = chatService.findChatById(req.getChatId());

        Message message = new Message();
        message.setChat(chat);
        message.setUser(user);
        message.setContent(req.getContent());
        message.setTimeStamp(LocalDateTime.now());
        
        return message; 
        
    }

    @Override
    public List<Message> getChatsMessages(Integer chatId,User reqUser) throws ChatException,UserException {
        Chat chat = chatService.findChatById(chatId);
        if(!chat.getUsers().contains(reqUser)){
            throw new UserException("you are not releted to this chat"+chat.getId());
        }
        List<Message> messages = messageRepository.findByChatId(chatId);

        return messages;
    }

    @Override
    public Message findMessageById(Integer messageId) throws MessageException {
        Optional<Message> opt = messageRepository.findById(messageId);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new MessageException("message not found with id " +messageId);
    }

    @Override
    public void deleteMessage(Integer messageId,User reqUser) throws MessageException,UserException {
        Message message = findMessageById(messageId);

        if(message.getUser().getId().equals(reqUser.getId())){
            messageRepository.deleteById(messageId);
        }
        throw new UserException("you can t delete another user's message "+reqUser.getFull_name());
    }
    
}
