package com.example.visitorproject.Repository;

import com.example.visitorproject.Entities.VisitorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<VisitorInfo,Integer> {

}
