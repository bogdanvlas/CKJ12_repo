package com.example.restful_demo_app.repository;

import com.example.restful_demo_app.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
}
