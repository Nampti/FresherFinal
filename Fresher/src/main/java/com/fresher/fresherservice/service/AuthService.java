package com.lthdv.authservice.service;

import com.lthdv.authservice.mapper.UserMapper;
import com.lthdv.authservice.model.User;
import com.lthdv.authservice.repository.table.UserRepository;
import com.lthdv.authservice.vo.enums.CoreErrorApp;
import com.lthdv.authservice.vo.request.RegisterRequest;
import com.lthdv.authservice.vo.response.AuthResponse;
import com.lthdv.authservice.vo.response.MessEntity;
import com.lthdv.authservice.vo.response.ResponseEntity;
import com.lthdv.authservice.vo.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final UserMapper userMapper;

    public AuthResponse login(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.map(user -> AuthResponse.builder()
                .user(userMapper.toUserResponse(user))
                .token(jwtService.generateToken(user))
                .build()).orElse(null);
    }

    public Object saveUser(RegisterRequest request) {
        User userDB = userRepository.findByUsername(request.getUser().getUsername()).orElse(null);
        if (userDB != null) {
            ResponseEntity responseEntity = new ResponseEntity();
            CoreErrorApp errorApp;
            errorApp = CoreErrorApp.SIGN_IN;
            MessEntity itemEntity = new MessEntity();
            itemEntity.setCode(errorApp.getCode());
            itemEntity.setDescription(errorApp.getDescription());
            responseEntity.setMess(itemEntity);
            return responseEntity;
        }

        User user = request.getUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole());
        User savedUser = userRepository.save(user);

        return userMapper.toUserResponse(savedUser);
    }


}
