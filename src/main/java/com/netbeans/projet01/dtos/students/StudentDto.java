package com.netbeans.projet01.dtos.students;

import com.netbeans.projet01.entities.Student;
import com.netbeans.projet01.utils.Validations.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record StudentDto(
        @NotEmpty(message = "le prenom est requis")
        @UniqueValue(domainClass = Student.class, fieldName = "firstName", message = "This name is already in use")
        @Size(min = 2,max = 100,message = "la taille du champs doit etre entre 2 et 100 caracteres")
        String firstName,
        @NotEmpty(message = "le nom est requis")
        @Size(min = 2,max = 100,message = "la taille du champs doit etre entre 2 et 100 caracteres")
        String lastName,
        @Email(message = "Adresse email not valide")
        @UniqueValue(domainClass = Student.class, fieldName = "email", message = "This email is already in use")
        String email,


       // @Positive(message = "la valeur attendue est un entier")
        Long schoolId)
{
}
