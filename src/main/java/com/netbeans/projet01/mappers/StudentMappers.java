package com.netbeans.projet01.mappers;

import com.netbeans.projet01.dtos.students.StudentDto;
import com.netbeans.projet01.dtos.students.StudentResponseDto;
import com.netbeans.projet01.entities.School;
import com.netbeans.projet01.entities.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMappers {
    public StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(student.getEmail(), student.getLastName(), student.getFirstName());
    }
    public Student toStudent(StudentDto studentDto) {
        Student student=new Student();
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setEmail(studentDto.email());

        School school=new School();
        school.setId(studentDto.schoolId());
        student.setSchool(school);
        return student;
    }
}
