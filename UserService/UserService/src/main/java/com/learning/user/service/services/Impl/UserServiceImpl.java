package com.learning.user.service.services.Impl;

import com.learning.user.service.entities.Rating;
import com.learning.user.service.entities.User;
import com.learning.user.service.exceptions.ResourceNotFoundException;
import com.learning.user.service.repositories.UserRepository;
import com.learning.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        // Generate unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        // Get all users from the database with the help of user repository
        List<User> allUsers = userRepository.findAll();

        for(User user : allUsers){
            // Fetch rating of each user from RATING SERVICE and write to each user
            ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserId(), ArrayList.class);
//            logger.info("{} ", ratingsOfUser);
            user.setRatings(ratingsOfUser);
        }
        return allUsers;
    }

    @Override
    public User getUser(String userId) {
        // Get user from database with the help of user repository
        User user =  userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server! : " + userId));

        // Fetch rating of above user from RATING SERVICE
        ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserId(), ArrayList.class);
        logger.info("{} ", ratingsOfUser);
        user.setRatings(ratingsOfUser);
        return user;
    }
}
