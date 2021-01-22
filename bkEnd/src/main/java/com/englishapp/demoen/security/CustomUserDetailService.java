package com.englishapp.demoen.security;

import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.englishapp.demoen.entity.User;
import com.englishapp.demoen.repository.UserRepository;
//import com.englishapp.demoen.security.testUser.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException(username);
            }
            return new JwtUser(user);
        }

        @Transactional
    public User loadUserById(Long id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null) new UserNotFoundException("User with id: " + id + " not found");
        return user;
    }
}
