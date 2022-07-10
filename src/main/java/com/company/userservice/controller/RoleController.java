package com.company.userservice.controller;

import com.company.userservice.dto.CreateRoleRequest;
import com.company.userservice.dto.RoleDto;
import com.company.userservice.model.Role;
import com.company.userservice.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody CreateRoleRequest request){
        return ResponseEntity.ok(roleService.createRole(request));
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
