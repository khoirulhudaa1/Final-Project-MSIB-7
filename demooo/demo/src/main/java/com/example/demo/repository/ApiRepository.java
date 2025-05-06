package com.example.demo.repository;

import com.example.demo.entity.ApiParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<ApiParameter, Long> {
}
