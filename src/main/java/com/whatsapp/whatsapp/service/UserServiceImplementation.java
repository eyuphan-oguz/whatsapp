package com.whatsapp.whatsapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.whatsapp.whatsapp.config.TokenProvider;
import com.whatsapp.whatsapp.exception.UserException;
import com.whatsapp.whatsapp.model.User;
import com.whatsapp.whatsapp.repository.UserRepository;
import com.whatsapp.whatsapp.request.UpdateUserRequest;
@Service
public class UserServiceImplementation implements UserService{

    private UserRepository userRepository;

    private TokenProvider tokenProvider;

    
    @Autowired
    public UserServiceImplementation(UserRepository userRepository,TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public User findByUserId(Integer id) throws UserException{
        Optional<User> opt =   userRepository.findById(id);

        if(opt.isPresent()){
            return opt.get();
        }

        throw new UserException("User not found with id" + id);
    }

    @Override
    public User findUserProfile(String jwt) throws UserException{
        String email = tokenProvider.getEmailToken(jwt);

        if(email==null){
            throw new BadCredentialsException("recieved invalid token---");
        }
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UserException("User not found with email"+email);
        }
        return user;
    }

    @Override
    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException {
        User user = findByUserId(userId);
        
        if(req.getFull_name() != null ){
            user.setFull_name(req.getFull_name());
        }

        if(req.getProfile_picture() != null){
            user.setProfile_pictures(req.getProfile_picture());
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> searchUser(String query) {
        List<User> users = userRepository.searchUser(query);
        return users;
    }
    
}
