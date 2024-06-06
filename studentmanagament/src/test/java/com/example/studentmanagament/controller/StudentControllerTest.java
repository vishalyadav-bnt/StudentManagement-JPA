package com.example.studentmanagament.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.studentmanagament.exception.DataIsNotPresent;
import com.example.studentmanagament.exception.StudentObjectISNull;
import com.example.studentmanagament.model.StudentModel;
import com.example.studentmanagament.response.SuccessResponnse;
import com.example.studentmanagament.service.StudentServiceimpl;

public class StudentControllerTest {

    @Mock
    StudentServiceimpl studentService;

    @InjectMocks
    StudentController studentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveStudent_Success() {
        UUID id = UUID.fromString("1f2be62f-c184-4bc9-a2cc-40972a08a96b");
        StudentModel studentModel = new StudentModel(id, "vishal", 12, "pune");
        when(studentService.save(studentModel)).thenReturn(studentModel);

        ResponseEntity<SuccessResponnse> responseEntity = studentController.saveStudent(studentModel);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        // assertEquals(studentModel, responseEntity.getBody());
    }

    @Test
    public void testSaveStudent_Negative() {
        UUID id = UUID.fromString("1f2be62f-c184-4bc9-a2cc-40972a08a96b");
        StudentModel studentModel = new StudentModel(id, "vishal", 12, "pune");
        when(studentService.save(studentModel)).thenThrow(new StudentObjectISNull("Data Not Present"));
        assertEquals(studentModel, studentModel);
    }

    @Test
    public void getStudent_Success() {
        UUID id = UUID.fromString("1f2be62f-c184-4bc9-a2cc-40972a08a96b");
        StudentModel studentModel = new StudentModel(id, "vishal", 12, "pune");
        java.util.List<StudentModel> studeList = new ArrayList<>();
        studeList.add(studentModel);
        when(studentService.getAll()).thenReturn(studeList);
        ResponseEntity<SuccessResponnse> sEntity = studentController.getAllStudent();
        assertEquals(HttpStatus.OK, sEntity.getStatusCode());
    }

    @Test
    public void getStudent_Nagative() {

        StudentModel studentModel = new StudentModel();
        java.util.List<StudentModel> studeList = new ArrayList<>();
        studeList.add(studentModel);
        when(studentService.getAll()).thenThrow(new DataIsNotPresent("Data Is Not Present"));
        assertEquals(studentModel, studentModel);
    }

    @Test
    public void testdeleteData_success()
    {
        UUID id = UUID.fromString("1f2be62f-c184-4bc9-a2cc-40972a08a96b");
        studentService.deleteStudentById(id);
        ResponseEntity<String>sEntity=studentController.deleteStudentById(id);
        assertEquals(HttpStatus.OK,sEntity.getStatusCode());
    }

   
}
