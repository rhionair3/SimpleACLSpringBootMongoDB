package com.rwds.login.repository;

import com.rwds.login.domain.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findBySurel(String surel);
}