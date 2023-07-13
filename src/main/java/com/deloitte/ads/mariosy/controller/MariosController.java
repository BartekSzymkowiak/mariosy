package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.entity.MariosEntity;
import com.deloitte.ads.mariosy.service.MariosyService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/marioses/")
public class MariosController {

    @Autowired
    private MariosyService mariosyService;

    @PostMapping
    public ResponseEntity createMarios(@RequestBody @NotNull MariosDTO mariosDTO){
        try{
            mariosyService.createMarios(mariosDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public Set<MariosDTO> getAllMarioses(){
        return mariosyService.getMarioses().stream().map(MariosDTO::mapMariosEntityToMariosDTO).collect(Collectors.toSet());
    }

    @GetMapping("/user/{userId}/created")
    public Set<MariosDTO> getMariosesCreatedByUser(@PathVariable("userId") Long userId){
        return mariosyService.getMariosesCreatedByUser(userId).stream().map(MariosDTO::mapMariosEntityToMariosDTO).collect(Collectors.toSet());
    }


    @GetMapping("/user/{userId}/received")
    public Set<MariosDTO> getSortedMariosesReceivedByUser(@PathVariable Long userId){
        return mariosyService.getMariosesReceivedByUser(userId).stream().map(MariosDTO::mapMariosEntityToMariosDTO).collect(Collectors.toSet());
    }


}
