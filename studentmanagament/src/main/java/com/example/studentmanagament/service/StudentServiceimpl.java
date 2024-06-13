package com.example.studentmanagament.service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.example.studentmanagament.exception.DataIsNotPresent;
import com.example.studentmanagament.exception.StudentIdIsNotFound;
import com.example.studentmanagament.exception.StudentObjectISNull;
import com.example.studentmanagament.model.StudentModel;
import com.example.studentmanagament.repositiory.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StudentServiceimpl implements StudentServices {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceimpl.class);
    @Autowired
    StudentRepository studentRepository;
    @Override
    @CacheEvict(value = "students", allEntries = true)
    public StudentModel save(StudentModel studentModel) {
      logger.info("Saving student data {}",studentModel);
       if(studentModel==null)
       {logger.error("Object is null");
        throw new StudentObjectISNull("Student Object Is Null");
       }
       StudentModel newStudent=studentRepository.save(studentModel);
       logger.info("Student data stored succesfully", newStudent);
       return newStudent;
    }
    @Override
    @CacheEvict(value = "students", key = "#id")
    public void deleteStudentById(UUID id) {
      logger.info("Deleting Data with {}",id);
       Optional<StudentModel> studentOptional = studentRepository.findById(id);
       if(studentOptional!=null&& !studentOptional.isEmpty())
       {  logger.info("Data Deleted with id:{}",id);
          studentRepository.deleteById(id);
       }else
       { logger.error("Id not foud for delete :{}",id);
            throw new StudentIdIsNotFound("Student id is not present");
       }
    }
    @Override
    @Cacheable(value = "students")
    public List<StudentModel> getAll() {
      logger.info("Fetching All Data.....");
       if(studentRepository.findAll().isEmpty())
       {logger.error("Data Is not present in database for fetching");
        throw new DataIsNotPresent("Data is not present");
       }
       List<StudentModel>list=studentRepository.findAll();
       logger.info("Fetch All Data");
       return list; 
    }
   @Override
   @CacheEvict(key ="id",value ="students")
   public StudentModel updateStudentById(UUID id, StudentModel studentModel) {
      logger.info("Uodating data with id:{}",id);
      Optional<StudentModel>stuOptional=studentRepository.findById(id);
      if(stuOptional.isPresent())
      {
         StudentModel studentModel2=stuOptional.get();
         studentModel2.setName(studentModel.getName());
         studentModel2.setCity(studentModel.getCity());
         studentModel2.setClassroom(studentModel.getClassroom());
         StudentModel updateStudent=studentRepository.save(studentModel2);
         logger.info("Data Updated SuccesFully :{}", studentModel2);
         return updateStudent;
      }
      else{logger.error("Id not foud for update:{}",id);
         throw new StudentIdIsNotFound("Id Not Found For Uodate StudentData");
      }
   }
   @Override
   @CacheEvict(key ="id",value ="students")
   public StudentModel updateStudentNameById(UUID id, String name) {
      logger.info("Uodating data with id:{}",id);
      Optional<StudentModel>stuOptional=studentRepository.findById(id);
      if(stuOptional.isPresent())
      {
         StudentModel studentModel2=stuOptional.get();
         studentModel2.setName(name);
         StudentModel updaStudent=studentRepository.save(studentModel2);
         logger.info("Data Updated SuccesFully :{}", studentModel2);
         return updaStudent;
      }
      else{logger.error("Id not foud for update :{}",id);
         throw new StudentIdIsNotFound("Id Not Found For Uodate StudentData");
      }
   }
    
   
 
}
