package com.aditya.kanban.repository;

import com.aditya.kanban.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
