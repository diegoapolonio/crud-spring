package com.diego.repository;

import com.diego.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course , Long>{
}
// jpa é uma interface que define as regras para que o hibernate possa implementar a comunicacao com o banco de dados