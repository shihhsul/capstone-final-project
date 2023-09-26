package com.fish.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fish.api.entitity.FishSchool;

@Repository
public interface FishSchoolRepository extends JpaRepository<FishSchool, Integer> {

}
