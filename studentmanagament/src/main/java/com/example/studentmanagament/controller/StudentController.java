package com.example.studentmanagament.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagament.model.StudentModel;
import com.example.studentmanagament.response.SuccessResponnse;
import com.example.studentmanagament.service.StudentServiceimpl;


@RestController
@RequestMapping("v1/student")
public class StudentController {
    @Autowired
    StudentServiceimpl studentServiceimpl;

    @PostMapping("/")
    public ResponseEntity<SuccessResponnse> save(@RequestBody StudentModel studentModel) {
        StudentModel studentModel2 = studentServiceimpl.save(studentModel);
        SuccessResponnse successResponnse=new SuccessResponnse("Data Store Succesfully...",HttpStatus.CREATED.value(),studentModel2);
        return new ResponseEntity<>(successResponnse,HttpStatus.CREATED);
    }

    
    @GetMapping
    public ResponseEntity<SuccessResponnse>getAllStudent(){
        List<StudentModel>list=studentServiceimpl.getAll();
        SuccessResponnse successResponnse=new SuccessResponnse("Data Fetch Succesfully...",HttpStatus.OK.value(),list);
        return new ResponseEntity<>(successResponnse,HttpStatus.OK);
    }

    // @DeleteMapping("{id}")
    // public ResponseEntity<String>deleteStudentById(@PathVariable("id") UUID id)
    // {System.out.println("sdsad");
    //    studentServiceimpl.deleteStudentById(id);
    //    return ResponseEntity.ok("Data Deleted Succesfully");
    // }


}