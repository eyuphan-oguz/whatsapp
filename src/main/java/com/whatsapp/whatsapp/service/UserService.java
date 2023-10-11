package com.whatsapp.whatsapp.service;

import java.util.List;

import com.whatsapp.whatsapp.exception.UserException;
import com.whatsapp.whatsapp.model.User;
import com.whatsapp.whatsapp.request.UpdateUserRequest;

public interface UserService {

    public User findByUserId(Integer id) throws UserException;

    public User findUserProfile(String jwt)  throws UserException;

    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException;

    public List<User>searchUser(String query);

}
