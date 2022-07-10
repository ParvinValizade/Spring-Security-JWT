package com.company.userservice.repository;

import com.company.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);

    @Query("from Role e where e.name in ?1")
    Optional<List<Role>> findByName(List<String> name);
}
