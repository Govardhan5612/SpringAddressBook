package com.bridgelabz.addressBook.model;

import com.bridgelabz.addressBook.dto.PersonDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PersonDetails"/*,uniqueConstraints = @UniqueConstraint(columnNames = "email")*/)
@Data
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    //@Column(name="email")
    private String email;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    public Person(PersonDto dto){
        firstName=dto.getFirstName();
        lastName=dto.getLastName();
        phoneNumber=dto.getPhoneNumber();
        email = dto.getEmail();
        address=dto.getAddress();
        city=dto.getCity();
        state=dto.getState();
        zipCode=dto.getZipCode();
    }
}
