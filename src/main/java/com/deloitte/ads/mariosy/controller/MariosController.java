package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.entity.MariosEntity;
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
import java.util.stream.Collectors;

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
        return mariosyService.getMarioses().stream().map(MariosDTO::mapMariosEntityToMariosDTO).collect(Collectors.toSet());
    }

    @GetMapping("/marioses/{mariosExternalId}")
    public ResponseEntity<MariosDTO> getMariosById(@PathVariable("mariosExternalId") UUID mariosExternalId){
        Optional<MariosEntity> mariosEntityOptional = mariosyService.getMariosByExternalId(mariosExternalId);
        if(mariosEntityOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            MariosDTO mariosDTO = MariosDTO.mapMariosEntityToMariosDTO(mariosEntityOptional.get());
            return new ResponseEntity<>(mariosDTO, HttpStatus.OK);
        }
    }

    @PostMapping("/marioses")
    public ResponseEntity<String> createMarios(@RequestBody @NotNull MariosDTO mariosDTO){
        try{
            mariosyService.createMarios(mariosDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(IllegalMariosFieldValueException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{userExternalId}/marioses/created")
    public Set<MariosDTO> getMariosesCreatedByUser(@PathVariable("userExternalId") UUID userExternalId){
        return mariosyService.getMariosesCreatedByUser(userExternalId).stream().map(MariosDTO::mapMariosEntityToMariosDTO).collect(Collectors.toSet());
    }

    @GetMapping("/users/{userExternalId}/marioses/received")
    public Set<MariosDTO> getSortedMariosesReceivedByUser(@PathVariable UUID userExternalId){
        return mariosyService.getMariosesReceivedByUser(userExternalId).stream().map(MariosDTO::mapMariosEntityToMariosDTO).collect(Collectors.toSet());
    }


}
