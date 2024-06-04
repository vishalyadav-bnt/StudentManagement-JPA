package com.example.studentmanagament.service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.studentmanagament.exception.DataIsNotPresent;
import com.example.studentmanagament.exception.StudentIdIsNotFound;
import com.example.studentmanagament.exception.StudentObjectISNull;
import com.example.studentmanagament.model.StudentModel;
import com.example.studentmanagament.repositiory.StudentRepository;

@Service
public class StudentServiceimpl implements StudentServices {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public StudentModel save(StudentModel studentModel) {
       if(studentModel==null)
       {
        throw new StudentObjectISNull("Student Object Is Null");
       }
       return studentRepository.save(studentModel);
    }
    @Override
    public void deleteStudentById(UUID id) {
       Optional<StudentModel> studentOptional = studentRepository.findById(id);
       if(studentOptional!=null&& !studentOptional.isEmpty())
       {
          studentRepository.deleteById(id);
       }else
       {
            throw new StudentIdIsNotFound("Student id is not present");
       }
    }
    @Override
    public List<StudentModel> getAll() {
       if(studentRepository.findAll().isEmpty())
       {
        throw new DataIsNotPresent("Data is not present");
       }
       return studentRepository.findAll();
    }
   @Override
   public StudentModel updateStudentById(UUID id, StudentModel studentModel) {
      Optional<StudentModel>stuOptional=studentRepository.findById(id);
      if(stuOptional.isPresent())
      {
         StudentModel studentModel2=stuOptional.get();
         studentModel2.setName(studentModel.getName());
         studentModel2.setCity(studentModel.getCity());
         studentModel2.setClassroom(studentModel.getClassroom());
         return studentRepository.save(studentModel2);
      }
      else{
         throw new StudentIdIsNotFound("Id Not Found For Uodate StudentData");
      }
   }
   @Override
   public StudentModel updateStudentNameById(UUID id, String name) {
      Optional<StudentModel>stuOptional=studentRepository.findById(id);
      if(stuOptional.isPresent())
      {
         StudentModel studentModel2=stuOptional.get();
         studentModel2.setName(name);
         return studentRepository.save(studentModel2);
      }
      else{
         throw new StudentIdIsNotFound("Id Not Found For Uodate StudentData");
      }
   }
    
   
 
}
