package com.studentmanagement.dao;

import com.studentmanagement.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Implementation of StudentDao interface
 * Uses Hibernate SessionFactory for database operations
 * Annotated with @Repository for Spring component scanning
 */
@Repository
public class StudentDaoImpl implements StudentDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * Save a new student to the database
     * @param student the student to save
     */
    @Override
    public void saveStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
    }
    
    /**
     * Retrieve a student by their ID
     * @param id the student ID
     * @return the Student object if found, null otherwise
     */
    @Override
    public Student getStudentById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Student.class, id);
    }
    
    /**
     * Update an existing student's information
     * @param student the student with updated information
     */
    @Override
    public void updateStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.update(student);
    }
    
    /**
     * Delete a student from the database
     * @param id the student ID to delete
     */
    @Override
    public void deleteStudent(int id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, id);
        if (student != null) {
            session.delete(student);
        }
    }
    
    /**
     * Retrieve all students from the database
     * @return List of all students
     */
    @Override
    public List<Student> getAllStudents() {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> query = session.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }
}
