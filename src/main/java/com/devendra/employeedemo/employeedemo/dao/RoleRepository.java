package com.devendra.employeedemo.employeedemo.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.devendra.employeedemo.employeedemo.model.ERole;
import com.devendra.employeedemo.employeedemo.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
