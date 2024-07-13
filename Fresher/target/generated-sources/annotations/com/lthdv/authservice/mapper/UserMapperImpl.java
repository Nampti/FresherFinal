package com.lthdv.authservice.mapper;

import com.lthdv.authservice.model.User;
import com.lthdv.authservice.vo.response.UserResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-13T21:47:37+0700",
    comments = "version: 1.6.0.Beta1, compiler: Eclipse JDT (IDE) 3.39.0.v20240620-1855, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setEmail( user.getEmail() );
        userResponse.setId( user.getId() );
        userResponse.setName( user.getName() );
        userResponse.setPhoneNumber( user.getPhoneNumber() );
        userResponse.setRole( user.getRole() );
        userResponse.setUsername( user.getUsername() );

        return userResponse;
    }
}
