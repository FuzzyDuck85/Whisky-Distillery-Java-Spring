package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {
    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> findWhiskiesFilterByYearDistilleryAgeName(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name="distillery", required = false) String distillery,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name= "region", required = false) String region
    ){
        if (year != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
        }
        if (age != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByAge(age), HttpStatus.OK);
        }
        if (distillery != null && age != null){
            return new ResponseEntity<>(whiskyRepository.findByDistilleryAndAge(distillery, age), HttpStatus.OK);
        }
        if (name != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByName(name), HttpStatus.OK);
        }
        if (region != null){
            return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/{id}")
    public ResponseEntity getWhiskys(@PathVariable Long id){
        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/whiskies")
    public ResponseEntity<Whisky> postWhisky(@RequestBody Whisky whisky){
        whiskyRepository.save(whisky);
        return new ResponseEntity<>(whisky, HttpStatus.CREATED);
    }


}
