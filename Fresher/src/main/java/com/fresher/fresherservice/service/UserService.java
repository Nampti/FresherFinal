package com.lthdv.authservice.service;

import com.lthdv.authservice.mapper.UserMapper;
import com.lthdv.authservice.repository.table.UserRepository;
import com.lthdv.authservice.vo.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserResponse getUserDetail(Long id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow());
    }

}
