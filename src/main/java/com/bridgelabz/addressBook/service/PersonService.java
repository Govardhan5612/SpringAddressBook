package com.bridgelabz.addressBook.service;

import com.bridgelabz.addressBook.dto.PersonDto;
import com.bridgelabz.addressBook.dto.ResponseDto;
import com.bridgelabz.addressBook.exception.PersonException;
import com.bridgelabz.addressBook.model.Person;
import com.bridgelabz.addressBook.repository.PersonRepo;
import com.bridgelabz.addressBook.util.JWT_Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepo repo;
    @Autowired
    JWT_Token token;
    public String addPerson(PersonDto dto) {
        Optional<Person> s = repo.findEmail(dto.getEmail());
       if (s.isEmpty()) {
            Person person = new Person(dto);

             repo.save(person);
           return token.createToken(person.getId());
        }
        else {
            return "This email already present in database";
        }
    }
    public List<Person> getAllPersons() {
        return repo.findAll();
    }
    public Person getPerson(String  tokenValue){
        int id = token.decode(tokenValue);
        return repo.findById(id).orElseThrow(()->new PersonException(id+" is not found"));
    }
    public Person update(String tokenValue,PersonDto person){
        int id = token.decode(tokenValue);
        Optional<Person> personData = repo.findById(id);
        if (personData.isPresent()){
            personData.get().setFirstName(person.getFirstName());
            personData.get().setLastName(person.getLastName());
            personData.get().setEmail(person.getEmail());
            personData.get().setAddress(person.getAddress());
            personData.get().setCity(person.getCity());
            personData.get().setState(person.getState());
            personData.get().setPhoneNumber(person.getPhoneNumber());
            personData.get().setZipCode(person.getZipCode());
            return repo.save(personData.get());
        }
        else {
            return repo.findById(id).orElseThrow(()-> new PersonException(id+" is not found"));
        }
    }
    public void delete(String tokenValue){
        int id = token.decode(tokenValue);
        repo.deleteById(id);
    }
    public List<Person> searchByCity(String city){
        return repo.searchByCity(city);
    }
    public List<Person> searchByState(String state){
        return repo.searchByState(state);
    }
    public List<Person> sortByName(){
        return repo.sortByName();
    }

    public List<Person> sortByCity(){
        return repo.sortByCity();
    }
}
