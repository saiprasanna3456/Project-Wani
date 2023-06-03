package com.example.pmwani.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pmwani.Entities.PdoManagement.PdoCategories;

@Repository
public interface PdoManagementRepository extends JpaRepository<PdoCategories,Integer> {
}
