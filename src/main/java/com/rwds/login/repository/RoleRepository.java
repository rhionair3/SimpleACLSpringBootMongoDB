package com.rwds.login.repository;

import com.rwds.login.domain.Role;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRole(String role);
}