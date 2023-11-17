package com.jira.demo.controller;

import com.jira.demo.model.Squad;
import com.jira.demo.service.SquadService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/squad")
@AllArgsConstructor

public class SquadController {


    private final SquadService squadService;

    @GetMapping
    public ResponseEntity<List<Squad>> getAllSquads() {
        return new ResponseEntity<>(squadService.getAllSquads(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Squad> getSquadById(@PathVariable("id") long squadId) {
        return new ResponseEntity<>(squadService.getSquadById(squadId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Squad> createSquad(@RequestBody Squad squad) {
        return new ResponseEntity<>(squadService.createSquad(squad), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Squad> updateSquad(@PathVariable("id") long squadId, @RequestBody Squad squad) {
        return new ResponseEntity<>(squadService.updateSquad(squadId, squad), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSquad(@PathVariable("id") long squadId) {
        squadService.deleteSquad(squadId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllSquads() {
        squadService.deleteAllSquads();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{squadId}/developers/{developerId}")
    public ResponseEntity<Squad> addEmployeeToSquad(@PathVariable Long squadId, @PathVariable Long developerId) {
        return new ResponseEntity<>(squadService.addEmployeeToSquad(squadId, developerId), HttpStatus.OK);
    }

    @DeleteMapping("{squadId}/developers/{developerId}")
    public ResponseEntity<Void> removeDeveloperFromSquad(@PathVariable Long squadId, @PathVariable Long developerId) {
        squadService.removeEmployeeFromSquad(squadId, developerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
