package com.bridgelabz.addressBook.controller;

import com.bridgelabz.addressBook.dto.PersonDto;
import com.bridgelabz.addressBook.dto.ResponseDto;
import com.bridgelabz.addressBook.model.Person;
import com.bridgelabz.addressBook.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonService service;
    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addPerson(@Valid @RequestBody PersonDto dto){
        ResponseDto responseDto = new ResponseDto("Adding Person Details : ",service.addPerson(dto));
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.ACCEPTED);
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getAllPersons(){
        List<Person> person =service.getAllPersons();
        ResponseDto dto = new ResponseDto("All Persons Data",person);
        return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
    }
    @GetMapping("/getById")
    public ResponseEntity<ResponseDto> getUser(@RequestHeader String token){
        Person person = service.getPerson(token);
        ResponseDto dto = new ResponseDto("Person details : ",person);
        return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
    }
    @PutMapping("/updateById")
    public ResponseEntity<ResponseDto> update(@RequestHeader String token,@Valid @RequestBody PersonDto dto){
        Person person = service.update(token,dto);
        ResponseDto resDto = new ResponseDto("Person data",person);
        return new ResponseEntity<>(resDto,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteById")
    public ResponseEntity<ResponseDto> delete(@RequestHeader String token){
        service.delete(token);
        ResponseDto dto = new ResponseDto("Person data is ","Deleted");
        return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
    }
    @GetMapping("/getCity/{city}")
    public ResponseEntity<ResponseDto> searchByCity(@PathVariable String city){
        List<Person> person = service.searchByCity(city);
            ResponseDto dto = new ResponseDto(city+" persons data",person);
            return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);

    }
    @GetMapping("getState/{state}")
    public ResponseEntity<ResponseDto> searchByState(@PathVariable String state){
        List<Person> list = service.searchByState(state);
            ResponseDto dto = new ResponseDto(state+" persons data",list);
            return  new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
    }
    @GetMapping("/sortByName")
    public ResponseEntity<ResponseDto> sortByName(){
        List<Person> list = service.sortByName();
        ResponseDto dto = new ResponseDto("First Name sorting order",list);
        return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/sortByCity")
    public ResponseEntity<ResponseDto> sortByCity(){
        List<Person> list = service.sortByCity();
        ResponseDto dto = new ResponseDto("City sorting order",list);
        return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
    }
}
