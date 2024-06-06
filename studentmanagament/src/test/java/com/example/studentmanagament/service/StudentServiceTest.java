package com.example.studentmanagament.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Optional;
import java.util.UUID;

import com.example.studentmanagament.exception.DataIsNotPresent;
import com.example.studentmanagament.exception.StudentIdIsNotFound;
import com.example.studentmanagament.exception.StudentObjectISNull;
import com.example.studentmanagament.model.StudentModel;
import com.example.studentmanagament.repositiory.StudentRepository;

public class StudentServiceTest {
    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceimpl studentServiceimpl;

    private StudentModel studentModel;
    @BeforeEach
    public void setUp()
    {   MockitoAnnotations.openMocks(this);
        studentModel=new StudentModel();
        studentModel.setStudent_id(UUID.randomUUID());
        studentModel.setName("vishal");
        studentModel.setCity("pune");
        studentModel.setClassroom(12);
    }

    @Test
    public void testSaveStudent_Success()
    {
        when(studentRepository.save(studentModel)).thenReturn(studentModel);
        StudentModel student=studentServiceimpl.save(studentModel);
       assertNotNull(student);
       assertEquals(studentModel.getStudent_id(), student.getStudent_id());
    }

    @Test
    public void testSaveStudent_Fail()
    {
        assertThrows(StudentObjectISNull.class, ()->{
            studentServiceimpl.save(null);
        });

    }
    @Test
     public void testGetAllStudents_Success() {
        List<StudentModel> students = new ArrayList<>();
        students.add(studentModel);
        when(studentRepository.findAll()).thenReturn(students);

        List<StudentModel> allStudents = studentServiceimpl.getAll();

        assertNotNull(allStudents);
        assertFalse(allStudents.isEmpty());
        assertEquals(1, allStudents.size());
        assertEquals(studentModel, allStudents.get(0));
    }

    @Test
    public void testGetAllStudents_fail() {
    assertThrows(DataIsNotPresent.class, ()->{
    studentServiceimpl.getAll();
     });
    }

    @Test
    public void testdeleteData_Success()
    {
        studentRepository.deleteById(studentModel.getStudent_id());
        assertEquals(studentModel,studentModel);
    }

    @Test
    public void testUpdateByName_Success()
    { 
        String name = "vishal";
        StudentModel studentMode1l = new StudentModel();
        when(studentRepository.findById(studentModel.getStudent_id())).thenReturn(Optional.of(studentModel));
        when(studentRepository.save(any(StudentModel.class))).thenReturn(studentMode1l);
        StudentModel result = studentServiceimpl.updateStudentNameById(studentModel.getStudent_id(), name);
        assertNotNull(result);
        assertEquals(studentMode1l, result);
    }

    @Test
    public void testUpdateByName_Fail()
    {
        assertThrows(StudentIdIsNotFound.class,()->{
            studentServiceimpl.updateStudentNameById(null, null);
        });
    }

    @Test
    public void testUpdateByRecord_Success()
    {
        String name = "vishal";
        int classroom = 12;
        String city = "pune";
        StudentModel studentModel1= new StudentModel();
        studentModel.setCity(city);
        studentModel.setClassroom(classroom);
        studentModel.setName(name);
        when(studentRepository.findById(studentModel.getStudent_id())).thenReturn(Optional.of(studentModel));
        when(studentRepository.save(any(StudentModel.class))).thenReturn(studentModel1);
        StudentModel sModel=studentServiceimpl.updateStudentById(studentModel.getStudent_id(), studentModel1);
        assertNotNull(sModel);
        assertEquals(studentModel1, sModel);
    }

    @Test
    public void testUpdateByRecord_Fail()
    {
        assertThrows(StudentIdIsNotFound.class,()->{
            studentServiceimpl.updateStudentNameById(null, null);
        });
    }



}
