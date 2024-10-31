package com.clayton.emit_api.auth.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.clayton.emit_api.auth.domain.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserDetails findByMail(String mail);
}
