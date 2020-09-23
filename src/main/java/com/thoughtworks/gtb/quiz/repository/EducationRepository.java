package com.thoughtworks.gtb.quiz.repository;

import com.thoughtworks.gtb.quiz.domain.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education,Integer> {
    List<Education> findAllByUserId(long userId);
}
