package com.example.SkillUpPlus.service;

import com.example.SkillUpPlus.client.GitHubClient;
import com.example.SkillUpPlus.client.GitHubUserDTO;
import com.example.SkillUpPlus.model.ObjectiveModel;
import com.example.SkillUpPlus.repository.ObjectiveRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectiveService {

    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Autowired
    private GitHubClient gitHubClient;

    // CREATE
    public ObjectiveModel createObjective(ObjectiveModel objective) {
        return objectiveRepository.save(objective);
    }

    // READ by ID
    public ObjectiveModel getObjectiveById(Long id) {
        return objectiveRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Objetivo não encontrado."));
    }

    // READ all
    public List<ObjectiveModel> getAllObjectives() {
        return objectiveRepository.findAll();
    }

    // UPDATE
    public ObjectiveModel updateObjective(Long id, ObjectiveModel updatedObjective) {
        ObjectiveModel existing = objectiveRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Objetivo não encontrado."));

        existing.setName(updatedObjective.getName());
        existing.setDescription(updatedObjective.getDescription());
        existing.setTargetDate(updatedObjective.getTargetDate());
        existing.setTargetValue(updatedObjective.getTargetValue());

        return objectiveRepository.save(existing);
    }

    // DELETE
    public void deleteObjectiveById(Long id) {
        try {
            objectiveRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Objetivo não encontrado.");
        }
    }

    // GitHub API
    public GitHubUserDTO getMentorProfile(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome de usuário do GitHub é obrigatório.");
        }
        return gitHubClient.getUserByUsername(username);
    }
}
