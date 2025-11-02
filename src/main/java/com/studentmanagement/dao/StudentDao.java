package com.studentmanagement.dao;

import com.studentmanagement.model.Student;
import java.util.List;

/**
 * Data Access Object (DAO) interface for Student entity
 * Defines CRUD operations for Student management
 */
public interface StudentDao {
    
    /**
     * Save a new student to the database
     * @param student the student to save
     */
    void saveStudent(Student student);
    
    /**
     * Retrieve a student by their ID
     * @param id the student ID
     * @return the Student object if found, null otherwise
     */
    Student getStudentById(int id);
    
    /**
     * Update an existing student's information
     * @param student the student with updated information
     */
    void updateStudent(Student student);
    
    /**
     * Delete a student from the database
     * @param id the student ID to delete
     */
    void deleteStudent(int id);
    
    /**
     * Retrieve all students from the database
     * @return List of all students
     */
    List<Student> getAllStudents();
}
