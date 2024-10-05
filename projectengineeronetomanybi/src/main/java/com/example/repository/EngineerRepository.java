package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Engineer;

public interface EngineerRepository extends JpaRepository<Engineer, Long> {

}
