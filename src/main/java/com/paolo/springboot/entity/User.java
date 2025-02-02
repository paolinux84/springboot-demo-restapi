package com.paolo.springboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // includes getter, setter, construction, hash and equal
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

   @NotBlank(message = "User Name cannot be empty!")

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

    private String username;
    private String password;
    //private String algo;
    //private String salt;
}
