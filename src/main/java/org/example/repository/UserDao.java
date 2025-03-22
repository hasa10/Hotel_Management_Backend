package org.example.repository;

import org.example.dto.User;
import org.example.entity.UserEntity;
import org.example.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUserRole(UserRole userRole);
}
