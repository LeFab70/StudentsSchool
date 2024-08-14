package com.netbeans.projet01.services.students;

import com.netbeans.projet01.dtos.students.StudentDto;
import com.netbeans.projet01.dtos.students.StudentResponseDto;

import java.util.List;

public interface StudentServicesInterface {
    StudentResponseDto saveStudent(StudentDto studentDto);
    List<StudentResponseDto> getAllStudents();
    List<StudentResponseDto> getStudent(long id);
    List<StudentResponseDto> getStudentsByName(String name);
    void deleteStudent(long id);
    StudentResponseDto updateStudent(long id, StudentDto updatedStudent);

}
