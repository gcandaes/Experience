package com.example.retourexperience.repository;

import com.example.retourexperience.ui.model.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, String> {
}
