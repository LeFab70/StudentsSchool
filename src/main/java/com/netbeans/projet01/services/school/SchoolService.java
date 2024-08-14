package com.netbeans.projet01.services.school;

import com.netbeans.projet01.dtos.schools.SchoolDto;
import com.netbeans.projet01.entities.School;
import com.netbeans.projet01.mappers.SchoolMappers;
import com.netbeans.projet01.repositories.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SchoolService implements SchoolServicesInterface{
    private final SchoolRepository schoolRepository;
    private  final SchoolMappers schoolMappers;

    @Override
    public SchoolDto createSchool(SchoolDto schoolDto) {
        School school=schoolMappers.toSchoolFromDto(schoolDto);
        schoolRepository.save(school);
        return schoolDto;
    }

    @Override
    public List<SchoolDto> getAllSchools() {
       return schoolRepository.findAll()
                .stream()
                .map(s->schoolMappers.toSchoolDtoFromSchool(s))
                .collect(Collectors.toList());
    }

    @Override
    public List<SchoolDto> getSchool(long id) {
        return schoolRepository.findById(id)
                .stream()
                //.map(this::toSchoolDtoFromSchool)
                .map(s->schoolMappers.toSchoolDtoFromSchool(s))
                .collect(Collectors.toList());
    }

    @Override
    public List<SchoolDto> getSchoolByName(String name) {
        return schoolRepository.findAllByNameContaining(name)
                .stream()
                // .map(this::toSchoolDtoFromSchool)
                .map(s->schoolMappers.toSchoolDtoFromSchool(s))
                .collect(Collectors.toUnmodifiableList());
    }
}
