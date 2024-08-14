package com.netbeans.projet01.controllers;

import com.netbeans.projet01.dtos.schools.SchoolDto;
import com.netbeans.projet01.services.school.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SchoolController {
//    private final SchoolRepository schoolRepository;
//  private  final SchoolMappers schoolMappers;
//    public SchoolController(SchoolRepository schoolRepository, SchoolMappers schoolMappers) {
//        this.schoolRepository = schoolRepository;
//        this.schoolMappers = schoolMappers;
//    }
private final SchoolService schoolService;
    @PostMapping("/schools")
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDto createSchool(
            @RequestBody SchoolDto schoolDto
    ){
       return schoolService.createSchool(schoolDto);
    }



    @GetMapping("/schools")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<SchoolDto> getAllSchools(){
        return schoolService.getAllSchools();
    }

    @GetMapping("/schools/{id}")
        public List<SchoolDto> getSchool(@PathVariable("id") long id){
        return schoolService.getSchool(id);
    }

    @GetMapping("/schools/search/{school-name}")
    public List<SchoolDto> getSchoolByName(@PathVariable("school-name") String name){
        return schoolService.getSchoolByName(name);
    }
}
