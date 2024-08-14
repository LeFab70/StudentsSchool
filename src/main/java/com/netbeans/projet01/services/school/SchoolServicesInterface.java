package com.netbeans.projet01.services.school;

import com.netbeans.projet01.dtos.schools.SchoolDto;

import java.util.List;

public interface SchoolServicesInterface {
    SchoolDto createSchool(SchoolDto schoolDto);
    List<SchoolDto> getAllSchools();
     List<SchoolDto> getSchool(long id);
    List<SchoolDto> getSchoolByName(String name);
}
