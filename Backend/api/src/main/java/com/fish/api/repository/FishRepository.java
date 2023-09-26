package com.fish.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fish.api.entitity.Fish;

@Repository
public interface FishRepository extends JpaRepository<Fish, String> {

}
