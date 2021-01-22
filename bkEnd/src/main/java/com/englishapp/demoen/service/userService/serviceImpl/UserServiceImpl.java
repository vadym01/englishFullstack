package com.englishapp.demoen.service.userService.serviceImpl;

import com.englishapp.demoen.entity.*;
import com.englishapp.demoen.exceptions.UsernameAlreadyExistException;
import com.englishapp.demoen.repository.BucketFolderRepository;
import com.englishapp.demoen.repository.RoleRepository;
import com.englishapp.demoen.repository.UserRepository;
import com.englishapp.demoen.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private BucketFolderRepository bucketFolderRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public User saveUser(User user) {
        try {
            Role roleUser = roleRepository.findByName("ROLE_USER");
            List<Role> userRole = new ArrayList<>();
            userRole.add(roleUser);

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRoles(userRole);
            user.setStatus(Status.ACTIVE);
            user.setConfirmPassword("");

            return userRepository.save(user);
        }catch (Exception e){
            throw new UsernameAlreadyExistException("Username: " + user.getUsername() + " already exist");
        }
    }


    @Override
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public User getUserObj() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return user;
    }


}
