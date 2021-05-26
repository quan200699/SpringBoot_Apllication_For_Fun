package com.example.startup.controller;

import com.example.startup.enumeration.RoleName;
import com.example.startup.exception.EmailUniqueException;
import com.example.startup.exception.RePasswordNotCorrectException;
import com.example.startup.exception.UsernameUniqueException;
import com.example.startup.model.dto.JwtResponse;
import com.example.startup.model.dto.LoginForm;
import com.example.startup.model.dto.SignUpForm;
import com.example.startup.model.entity.Role;
import com.example.startup.model.entity.User;
import com.example.startup.service.jwt.JwtService;
import com.example.startup.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(loginForm.getUsername()).get();
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<SignUpForm> register(@Valid @RequestBody SignUpForm signUpForm) throws UsernameUniqueException, EmailUniqueException, RePasswordNotCorrectException {
        Optional<User> userOptional = userService.findByUsername(signUpForm.getUsername());
        if (userOptional.isPresent()) {
            throw new UsernameUniqueException();
        }
        userOptional = userService.findByEmail(signUpForm.getEmail());
        if (userOptional.isPresent()) {
            throw new EmailUniqueException();
        }
        if (!signUpForm.getPassword().equals(signUpForm.getRePassword())) {
            throw new RePasswordNotCorrectException();
        }
        User user = new User();
        user.setUsername(signUpForm.getUsername());
        user.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        user.setEmail(signUpForm.getEmail());
        user.getRoles().add(new Role(2L, RoleName.ROLE_USER.toString()));
        userService.save(user);
        return new ResponseEntity<>(signUpForm, HttpStatus.OK);
    }
}
