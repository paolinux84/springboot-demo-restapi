package com.paolo.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Data // includes getter, setter, construction, hash and equal
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    @NotBlank(message = "Department Name cannot be empty!")

    /**
     * many different kind of validation annotations
     *
     * https://medium.com/emlakjet/request-validation-with-spring-boot-annotations-515d5cae330e
     */
//    @Length(max = 22, min = 1) //hibernate standard annotation (same behaviour)
//    @Size(max = 5, min = 1) //jakarta standard annotation (same behaviour)
//    @Email
//    @Positive
//    @Negative
//    @NegativeOrZero
//    @Pattern()
//    @Future
//    @PastOrPresent

    private String name;
    private String address;
    private String code;

}
