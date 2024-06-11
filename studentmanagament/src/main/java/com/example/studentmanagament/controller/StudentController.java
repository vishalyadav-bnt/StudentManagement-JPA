package com.example.studentmanagament.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagament.model.StudentModel;
import com.example.studentmanagament.response.SuccessResponnse;
import com.example.studentmanagament.service.StudentServiceimpl;
import org.slf4j.Logger;

@RestController
@RequestMapping("v1/student")
public class StudentController {
        private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentServiceimpl studentServiceimpl;

    @PostMapping("/")
    public ResponseEntity<SuccessResponnse> saveStudent(@RequestBody StudentModel studentModel) {
        logger.info("Received request to save student data");
        StudentModel newStudent = studentServiceimpl.save(studentModel);
        logger.info("Student data saved successfully");
        SuccessResponnse successResponnse=new SuccessResponnse("Data Store Succesfully...",HttpStatus.CREATED.value(),newStudent);
        return new ResponseEntity<>(successResponnse,HttpStatus.CREATED);
    }

    
    @GetMapping
    public ResponseEntity<SuccessResponnse>getAllStudent(){
        logger.info("Recieved Request for fetch all data");
        List<StudentModel>list=studentServiceimpl.getAll();
        logger.info("Fetch All Data successfully...");
        SuccessResponnse successResponnse=new SuccessResponnse("Data Fetch Succesfully...",HttpStatus.OK.value(),list);
        return new ResponseEntity<>(successResponnse,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteStudentById(@PathVariable("id") UUID id)
    {  logger.info("Received request for delete data  with id :{}",id);
       studentServiceimpl.deleteStudentById(id);
       logger.info("Data Deleted Succesfully with id {}", id);
       return ResponseEntity.ok("Data Deleted Succesfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<SuccessResponnse>updateStudentRecordById(@PathVariable("id")UUID id,@RequestBody StudentModel studentModel)
    {   logger.info("Received request for update data  with id :{}",id);
        StudentModel studentModel2=studentServiceimpl.updateStudentById(id,studentModel);
        logger.info("Update  data Succesfully with id :{}",id);
        SuccessResponnse successResponnse=new SuccessResponnse("Data Updated Succesfully",HttpStatus.OK.value(),studentModel2);
        return new ResponseEntity<>(successResponnse,HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<SuccessResponnse>updateStudentNameById(@PathVariable("id")UUID id,@RequestParam("name")String name){
        logger.info("Request recieved for update name by using id");
        StudentModel studentModel=studentServiceimpl.updateStudentNameById(id,name);
        logger.info("Student name update succesfully by using name");
        SuccessResponnse successResponnse=new SuccessResponnse("Name Updated succesfully",HttpStatus.OK.value(),studentModel);
        return new ResponseEntity<>(successResponnse,HttpStatus.OK);
    }

}