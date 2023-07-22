package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.service.IllegalMariosFieldValueException;
import com.deloitte.ads.mariosy.service.MariosyService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public List<MariosDTO> getAllMarioses(){
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
            return new ResponseEntity<>(returnMariosDTO, HttpStatus.CREATED);
        }catch(IllegalMariosFieldValueException e){
            MariosDTO emptyMariosDTO = new MariosDTO();
            emptyMariosDTO.setAdditionalMessage(e.getMessage());
            return new ResponseEntity<>(emptyMariosDTO, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{userExternalId}/marioses/created")
    public List<MariosDTO> getMariosesCreatedByUser(@PathVariable("userExternalId") UUID userExternalId){
        return mariosyService.getMariosesDTOsCreatedByUser(userExternalId);
    }

    @GetMapping("/users/{userExternalId}/marioses/received")
    public List<MariosDTO> getSortedMariosesReceivedByUser(@PathVariable UUID userExternalId){
        return mariosyService.getMariosesDTOsReceivedByUser(userExternalId);
    }

    @DeleteMapping("/marioses/{mariosExternalId}")
    public ResponseEntity deleteMarios(@PathVariable UUID mariosExternalId){
        mariosyService.deleteMarios(mariosExternalId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
