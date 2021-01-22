package com.englishapp.demoen.controller.authentication;

//import io.agileintelligence.ppmtool.domain.User;
//import io.agileintelligence.ppmtool.services.MapValidationErrorService;
//import io.agileintelligence.ppmtool.services.UserService;
//import io.agileintelligence.ppmtool.validator.UserValidator;
import com.englishapp.demoen.entity.User;
import com.englishapp.demoen.entity.dto.AuthenticationRequestDto;
import com.englishapp.demoen.security.jwt.JwtLoginSuccessResponse;
import com.englishapp.demoen.security.jwt.JwtTokenProvider;
import com.englishapp.demoen.service.MapValidationErrorService;
import com.englishapp.demoen.service.userService.UserService;
import com.englishapp.demoen.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.englishapp.demoen.InfoProvider.TOKEN_PREFIX;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody User user, BindingResult result){
        // Validate passwords match
        userValidator.validate(user,result);


        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null){
            return errorMap;
        }

        User newUser = userService.saveUser(user);

        return  new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody AuthenticationRequestDto credentials, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtLoginSuccessResponse(true,jwt));

    }
}