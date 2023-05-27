package com.example.visitorproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.visitorproject.Entities.PdoManagement.PdoCategories;

@Repository
public interface PdoManagementRepository extends JpaRepository<PdoCategories,Integer> {
}
