package com.example.studentmanagament.repositiory;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentmanagament.model.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel,UUID>  {
    
}

