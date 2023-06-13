package com.bridgelabz.addressBook.repository;

import com.bridgelabz.addressBook.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Integer> {
    @Query(value = "select * from person_details like city = :city", nativeQuery = true)
    List<Person> searchByCity(String city);

    @Query(value = "select * from person_details like state = :state", nativeQuery = true)
    List<Person> searchByState(String state);

    @Query(value = "select * from person_details where email = :email", nativeQuery = true)
    Optional<Person> findEmail(String email);

    @Query(value = "SELECT * FROM person_details ORDER BY first_name ASC;", nativeQuery = true)
    List<Person> sortByName();

    @Query(value = "SELECT * FROM person_details ORDER BY city ASC;", nativeQuery = true)
    List<Person> sortByCity();

}
