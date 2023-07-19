package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.service.IllegalMariosFieldValueException;
import com.deloitte.ads.mariosy.service.MariosyService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class MariosController {
    private MariosyService mariosyService;

    @Autowired
    public MariosController(MariosyService mariosyService) {
        this.mariosyService = mariosyService;
    }

    @GetMapping("/marioses")
    public Set<MariosDTO> getAllMarioses(){
        return mariosyService.getMariosesDTOs();
    }

    @GetMapping("/marioses/{mariosExternalId}")
    public ResponseEntity<MariosDTO> getMariosById(@PathVariable("mariosExternalId") UUID mariosExternalId){
        Optional<MariosDTO> mariosDTOOptional = mariosyService.getMariosDTOByExternalId(mariosExternalId);
        if(mariosDTOOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(mariosDTOOptional.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/marioses")
    public ResponseEntity<MariosDTO> createMarios(@RequestBody @NotNull MariosDTO mariosDTO){
        try{
             MariosDTO returnMariosDTO = mariosyService.createMarios(mariosDTO);
            return new ResponseEntity<MariosDTO>(returnMariosDTO, HttpStatus.CREATED);
        }catch(IllegalMariosFieldValueException e){
            return new ResponseEntity<MariosDTO>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<MariosDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{userExternalId}/marioses/created")
    public Set<MariosDTO> getMariosesCreatedByUser(@PathVariable("userExternalId") UUID userExternalId){
        return mariosyService.getMariosesDTOsCreatedByUser(userExternalId);
    }

    @GetMapping("/users/{userExternalId}/marioses/received")
    public Set<MariosDTO> getSortedMariosesReceivedByUser(@PathVariable UUID userExternalId){
        return mariosyService.getMariosesDTOsReceivedByUser(userExternalId);
    }

}
