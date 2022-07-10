package com.company.userservice.dto.converter;

import com.company.userservice.dto.RoleDto;
import com.company.userservice.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleDtoConverter {

    public RoleDto convert(Role from){
        return new RoleDto(
                from.getId(),
                from.getName());
    }

    public List<RoleDto> convert(List<Role> fromList){
        return fromList.stream()
                .map(this::convert).collect(Collectors.toList());
    }
}
