package com.example.studentmanagament.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.example.studentmanagament.exception.StudentIdIsNotFound;
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
    public void testdeleteData_success() {
        UUID id = UUID.fromString("1f2be62f-c184-4bc9-a2cc-40972a08a96b");
        studentService.deleteStudentById(id);
        ResponseEntity<String> sEntity = studentController.deleteStudentById(id);
        assertEquals(HttpStatus.OK, sEntity.getStatusCode());
    }
    @Test
    public void testUpdateData_Success() {
        UUID id = UUID.fromString("1f2be62f-c184-4bc9-a2cc-40972a08a96b");
        StudentModel studentModel = new StudentModel();
        String name = "vishal";
        int classroom = 12;
        String city = "pune";
        studentModel.setCity(city);
        studentModel.setClassroom(classroom);
        studentModel.setName(name);
        when(studentService.updateStudentById(id, studentModel)).thenReturn(studentModel);
        ResponseEntity<SuccessResponnse> responseEntity = studentController.updateStudentRecordById(id, studentModel);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Data Updated Succesfully", responseEntity.getBody().getMessage());
        verify(studentService, times(1)).updateStudentById(eq(id), eq(studentModel));

    }
    @Test
    public void testUpdateData_Failure_StudentNotFound() {
        UUID id = UUID.fromString("1f2be62f-c184-4bc9-a2cc-40972a08a96b");
        String name = "vishal";
        int classroom = 12;
        String city = "pune";
        StudentModel studentModel = new StudentModel();
        studentModel.setCity(city);
        studentModel.setClassroom(classroom);
        studentModel.setName(name);
       when(studentService.updateStudentById(any(UUID.class), any(StudentModel.class)))
            .thenThrow(new StudentIdIsNotFound("Student not found"));
        assertThrows(StudentIdIsNotFound.class, () -> {
            studentController.updateStudentRecordById(id, studentModel);
        });
        verify(studentService, times(1)).updateStudentById(eq(id), eq(studentModel));
    }

    @Test
    public void testUpdateByName_Success()
    {
        UUID id = UUID.fromString("1f2be62f-c184-4bc9-a2cc-40972a08a96b");
        String name = "vishal";
        StudentModel studentModel=new StudentModel();
        when(studentService.updateStudentNameById(id, name)).thenReturn(studentModel);
        ResponseEntity<SuccessResponnse>sEntity=studentController.updateStudentNameById(id, name);
        assertEquals(HttpStatus.OK, sEntity.getStatusCode());
        assertNotNull(sEntity.getBody());
        assertEquals("Name Updated succesfully", sEntity.getBody().getMessage());
        verify(studentService, times(1)).updateStudentNameById(id, name);

    }
    @Test
    public void testUpdateByName_Nagative()
    {
        UUID id = UUID.fromString("1f2be62f-c184-4bc9-a2cc-40972a08a96b");
        String name = "vishal";
        when(studentService.updateStudentNameById(id, name)).thenThrow(new StudentIdIsNotFound(name));
        assertThrows(StudentIdIsNotFound.class,()->{
        studentController.updateStudentNameById(id, name);
        });
        verify(studentService,times(1)).updateStudentNameById(id,name);

    }
}
