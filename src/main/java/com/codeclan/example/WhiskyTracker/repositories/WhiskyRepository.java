package com.codeclan.example.WhiskyTracker.repositories;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.sun.codemodel.internal.JWhileLoop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface WhiskyRepository extends JpaRepository<Whisky, Long> {
    List<Whisky> findWhiskiesByYear(Integer year);
    List<Whisky> findWhiskiesByAge(Integer age);
    List<Whisky> findWhiskiesByName(String name);
    List<Whisky> findByDistilleryAndAge(String name, int age);

}
