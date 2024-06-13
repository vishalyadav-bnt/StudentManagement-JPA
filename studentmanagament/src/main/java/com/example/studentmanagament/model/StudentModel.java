package com.example.studentmanagament.model;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="Student_details")
public class StudentModel implements Serializable {
    @Id
    @GeneratedValue(strategy =GenerationType.UUID)
    @Column(name ="Student_id")
    private UUID student_id;
    @Column(name ="Student_Name",nullable = false)
    private String name;
    @Column(name ="Student_Class")
    private int classroom;
    @Column(name ="Student_City")
    private String city;
}

