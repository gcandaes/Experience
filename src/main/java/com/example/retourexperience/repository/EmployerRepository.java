package com.example.retourexperience.repository;

import com.example.retourexperience.ui.model.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, String> {
}
