package com.company.userservice.service;

import com.company.userservice.dto.CreateUserRequest;
import com.company.userservice.dto.UserDto;
import com.company.userservice.dto.converter.UserDtoConverter;
import com.company.userservice.model.User;
import com.company.userservice.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserDtoConverter converter;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserDtoConverter converter, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->
        authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    public UserDto createUser(CreateUserRequest request){
        roleService.isEmptyRoleList(request.getRoles());
        User user = new User(
                request.getName(),
                request.getUsername(),
                request.getPassword(),
                roleService.findRoleByName(request.getRoles())
        );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return converter.convert(userRepository.save(user));
    }

    public List<UserDto> getAllUsers(){
        return converter.convert(userRepository.findAll());
    }
}
