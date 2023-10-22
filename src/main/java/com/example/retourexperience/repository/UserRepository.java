package com.example.retourexperience.repository;

import com.example.retourexperience.ui.model.responseEntity.UserRest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRest, String> {
}
