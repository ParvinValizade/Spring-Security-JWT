package com.company.userservice.dto.converter;

import com.company.userservice.dto.UserDto;
import com.company.userservice.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    private final RoleDtoConverter converter;

    public UserDtoConverter(RoleDtoConverter converter) {
        this.converter = converter;
    }

    public UserDto convert(User from){
        return new UserDto(from.getId(),
                from.getName(),
                from.getUsername(),
                from.getPassword(),
                converter.convert(new ArrayList<>(from.getRoles())));
    }

    public List<UserDto> convert(List<User> fromList){
        return fromList.stream()
                .map(this::convert).collect(Collectors.toList());
    }
}
