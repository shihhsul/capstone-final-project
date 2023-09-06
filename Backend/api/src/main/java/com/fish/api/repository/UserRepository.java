package com.fish.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fish.api.entitity.Aquarium;
import com.fish.api.entitity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
