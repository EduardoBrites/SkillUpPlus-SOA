package com.example.SkillUpPlus.controller;

import com.example.SkillUpPlus.model.ObjectiveModel;
import com.example.SkillUpPlus.service.ObjectiveService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/objectives")
public class ObjectiveController {

    @Autowired
    private ObjectiveService objectiveService;

    // CREATE
    @PostMapping
    public ResponseEntity<Object> createObjective(@Valid @RequestBody ObjectiveModel objective) {
        ObjectiveModel saved = objectiveService.createObjective(objective);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // READ (get by id)
    @GetMapping("/{id}")
    public ResponseEntity<Object> getObjective(@PathVariable Long id) {
        try {
            ObjectiveModel objective = objectiveService.getObjectiveById(id);
            return new ResponseEntity<>(objective, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // LIST (get all)
    @GetMapping
    public ResponseEntity<List<ObjectiveModel>> getAllObjectives() {
        return ResponseEntity.ok(objectiveService.getAllObjectives());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateObjective(
            @PathVariable Long id,
            @Valid @RequestBody ObjectiveModel objective) {

        try {
            ObjectiveModel updated = objectiveService.updateObjective(id, objective);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteObjective(@PathVariable Long id) {
        try {
            objectiveService.deleteObjectiveById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
