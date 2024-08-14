package com.netbeans.projet01.controllers;

import com.netbeans.projet01.dtos.students.StudentDto;
import com.netbeans.projet01.dtos.students.StudentResponseDto;
import com.netbeans.projet01.services.students.StudentServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController

public class StudentController {
  private final StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }


    @PostMapping("/student")
    public StudentResponseDto saveStudent(@Valid @RequestBody StudentDto studentDto) {
        //System.out.println(studentDto.toString());
      return this.studentServices.saveStudent(studentDto);
    }


    @GetMapping("/student")
    public List<StudentResponseDto> getAllStudents() {
        return studentServices.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public List<StudentResponseDto> getStudent(@PathVariable("id") long id) {
        return studentServices.getStudent(id);
    }

    @GetMapping("/student/search/{student-name}")
    public List<StudentResponseDto> getStudentsByName(@PathVariable("student-name") String name) {
        return studentServices.getStudentsByName(name);
    }

    @DeleteMapping("/student/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteStudent(@PathVariable("id") long id) {
       studentServices.deleteStudent(id);
    }

    @PutMapping("/student/{id}")
    public StudentResponseDto updateStudent(@PathVariable("id") long id, @RequestBody StudentDto updatedStudentDto) {
        return studentServices.updateStudent(id,updatedStudentDto);

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleExceptionValidation(MethodArgumentNotValidException exception){
        var errors=new HashMap<String,String>();
        exception.getBindingResult().getAllErrors().forEach(
                error->{
                    var fieldName=((FieldError) error).getField();
                    var errorMessage=error.getDefaultMessage();
                    errors.put(fieldName,errorMessage);
                     }
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
}