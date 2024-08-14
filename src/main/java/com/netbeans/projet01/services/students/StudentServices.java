package com.netbeans.projet01.services.students;

import com.netbeans.projet01.dtos.students.StudentDto;
import com.netbeans.projet01.dtos.students.StudentResponseDto;
import com.netbeans.projet01.entities.Student;
import com.netbeans.projet01.mappers.StudentMappers;
import com.netbeans.projet01.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class StudentServices implements StudentServicesInterface {
    private final StudentRepository studentRepository;
    private final StudentMappers studentMappers;

//    public StudentServices(StudentRepository studentRepository, StudentMappers studentMappers) {
//        this.studentRepository = studentRepository;
//        this.studentMappers = studentMappers;
//    }

    @Override
    public StudentResponseDto saveStudent(StudentDto studentDto) {
        Student student = studentMappers.toStudent(studentDto);
        StudentResponseDto studentDtoSave = studentMappers.toStudentResponseDto(studentRepository.save(student));
        return studentDtoSave;
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(s -> studentMappers.toStudentResponseDto(s))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDto> getStudent(long id) {
        return studentRepository.findById(id)
                .stream()
                //.map(this:: toStudentResponseDto)
                .map(s -> studentMappers.toStudentResponseDto(s))
                .collect(Collectors.toList());
    }


    @Override
    public List<StudentResponseDto> getStudentsByName(String name) {
        return studentRepository.findAllByFirstNameContaining(name)
                .stream()
                .map(s -> studentMappers.toStudentResponseDto(s))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentResponseDto updateStudent(long id, StudentDto updatedStudent) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            Student updateStd = studentMappers.toStudent(updatedStudent);
            existingStudent.setFirstName(updateStd.getFirstName());
            existingStudent.setLastName(updateStd.getLastName());
            //existingStudent.setBirthDay(upadtedStudent.getBirthDay());
            //existingStudent.setSexe(upadtedStudent.getSexe());
            existingStudent.setEmail(updateStd.getEmail());
            existingStudent.setSchool(updateStd.getSchool());
            // System.out.println(existingStudent.toString());
            return studentMappers.toStudentResponseDto(studentRepository.save(existingStudent));
            // studentRepository.save(student);
        } else {
            throw new RuntimeException("student not found");
        }
    }

}