package com.company.userservice.service;

import com.company.userservice.dto.CreateRoleRequest;
import com.company.userservice.dto.RoleDto;
import com.company.userservice.dto.converter.RoleDtoConverter;
import com.company.userservice.exception.RoleNotFoundException;
import com.company.userservice.model.Role;
import com.company.userservice.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleDtoConverter converter;

    public RoleService(RoleRepository roleRepository, RoleDtoConverter converter) {
        this.roleRepository = roleRepository;
        this.converter = converter;
    }

    public RoleDto createRole(CreateRoleRequest request){
        Role role = new Role(
                request.getName()
        );
        return converter.convert(roleRepository.save(role));
    }

    public List<RoleDto> getAllRoles() {
        return converter.convert(roleRepository.findAll());
    }
    protected List<Role> findRoleByName(List<String> roleName){
        return roleRepository.findByName(roleName)
                .orElseThrow(
                        () -> new RoleNotFoundException("Role could not find by name:" + roleName)
                );
    }

    protected void isEmptyRoleList(List<String> roleName){
       if (findRoleByName(roleName).isEmpty()){
           throw new RoleNotFoundException("Role could not find by name:" + roleName);
       }
    }


}
