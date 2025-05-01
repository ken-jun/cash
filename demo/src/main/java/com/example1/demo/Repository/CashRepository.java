package com.example1.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example1.demo.entity.Cash;
@Repository
public interface CashRepository extends JpaRepository<Cash, Integer> {
}