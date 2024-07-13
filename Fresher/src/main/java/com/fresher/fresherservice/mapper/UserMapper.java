package com.lthdv.authservice.mapper;

import com.lthdv.authservice.model.User;
import com.lthdv.authservice.vo.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);
}
