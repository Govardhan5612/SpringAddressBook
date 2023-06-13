package com.bridgelabz.addressBook.dto;

import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
public class PersonDto {
    @Pattern(regexp = "[A-Z]{1}[a-z]{2,}", message = "Invalid First name")
    @NotBlank(message = "Not take Blank values")
    private String firstName;
    @NotBlank(message = "Not give Blank values")
    @Pattern(regexp = "[A-Z]{1}[a-z]{2,}", message = "Invalid Last name")
    private String lastName;
    @NotBlank(message = "Not take Blank values")
    @Pattern(regexp = "[9876]{1}[0-9]{9}",message = "Invalid phone number")
    private String phoneNumber;
    @NotBlank(message = "Not take Blank values")
  //  @Pattern(regexp = "[a-zA-Z0-9]{6,25}@gmail.com",message = "Invalid email address")
    @Email(regexp = "[a-zA-Z0-9]{6,25}@gmail.com",message = "Invalid email address" )
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Not give Blank values")
    @Pattern(regexp = "[A-Z]{1}[a-z]{2,}", message = "Invalid Address")
    private String address;
    @NotBlank(message = "Not give Blank values")
    @Pattern(regexp = "[A-Z]{1}[a-z]{2,}", message = "Invalid city name")
    private String city;
    @NotBlank(message = "Not give Blank values")
    @Pattern(regexp = "[A-Z]{1}[a-z]{2,}[\\s]{0,1}[A-Z]{0,1}[a-z]{0,}", message = "Invalid State name")
    private String state;
    @NotBlank(message = "Not give Blank values")
    @Pattern(regexp = "[0-9]{6}", message = "Invalid Zip code")
    private String zipCode;
}
