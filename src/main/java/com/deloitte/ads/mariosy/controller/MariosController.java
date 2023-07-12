package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.repository.Marios;
import com.deloitte.ads.mariosy.service.Mariosy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/marioses/")
public class MariosController {

    @Autowired
    private Mariosy mariosy;

    @PostMapping
    public ResponseEntity<Object> createMarios(@RequestBody MariosDTO mariosDTO){
         Integer mariosId =  mariosy.createMarios(mariosDTO.getCreatorId(),
                 mariosDTO.getReceiversIds(),
                 mariosDTO.getType(),
                 mariosDTO.getComment());
         if (mariosId >= 0){
             Map<String, Object> object = new HashMap<>();
             object.put("id", mariosId);
             return new ResponseEntity<>(object, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public Set<Marios> getAllMarioses(){
        return mariosy.getMarioses();
    }

    @GetMapping("/user/{userId}/created")
    public List<Marios> getSortedMariosesCreatedByUser(@PathVariable Integer userId){
        return mariosy.getSortedMariosesCreatedByUser(userId);
    }

    @GetMapping("/user/{userId}/received")
    public List<Marios> getSortedMariosesReceivedByUser(@PathVariable Integer userId){
        return mariosy.getSortedMariosesReceivedByUser(userId);
    }


}
