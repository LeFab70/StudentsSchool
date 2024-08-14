package com.netbeans.projet01.mappers;

import com.netbeans.projet01.dtos.schools.SchoolDto;
import com.netbeans.projet01.entities.School;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SchoolMappers {
    public SchoolDto toSchoolDtoFromSchool(School school){
        return new SchoolDto(school.getName());
    }
    public School toSchoolFromDto(SchoolDto schoolDto){
        School school=new School();
        BeanUtils.copyProperties(schoolDto,school);//source,destination
        //school.setName(schoolDto.name());
        return school;
    }
}
