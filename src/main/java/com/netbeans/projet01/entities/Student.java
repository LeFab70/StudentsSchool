package com.netbeans.projet01.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.netbeans.projet01.enums.Sexe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 100)
    private  String firstName;
    @Column(length = 100)
    private String lastName;
    @Column(length = 100,unique = true)
    private  String email;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDay;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private StudentProfile studentProfile;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "school_id",nullable = false)
    @JsonBackReference
    private School school;

}
