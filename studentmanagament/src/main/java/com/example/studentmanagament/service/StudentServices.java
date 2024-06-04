package com.example.studentmanagament.service;

import java.util.List;
import java.util.UUID;


import com.example.studentmanagament.model.StudentModel;

public interface StudentServices {
public StudentModel save(StudentModel studentModel);
public void deleteStudentById(UUID id);
public List<StudentModel> getAll();
public StudentModel updateStudentById(UUID id,StudentModel studentModel);
public StudentModel updateStudentNameById(UUID id,String name);
}
