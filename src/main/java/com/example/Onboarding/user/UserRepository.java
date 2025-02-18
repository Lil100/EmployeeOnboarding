package com.example.Onboarding.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // This method finds a user by username
    UserEntity findByUsername(String username);
}
