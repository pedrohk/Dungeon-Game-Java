package com.pedrohk.Dungeon_Game_Java.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedrohk.Dungeon_Game_Java.model.DungeonResult;
import com.pedrohk.Dungeon_Game_Java.repository.DungeonResultRepository;
import com.pedrohk.Dungeon_Game_Java.service.DungeonGameSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dungeon")
public class DungeonGameController {

    private final DungeonGameSolver dungeonGameSolver;
    private final DungeonResultRepository dungeonResultRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public DungeonGameController(DungeonGameSolver dungeonGameSolver,
                                 DungeonResultRepository dungeonResultRepository,
                                 ObjectMapper objectMapper) {
        this.dungeonGameSolver = dungeonGameSolver;
        this.dungeonResultRepository = dungeonResultRepository;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/solve")
    public ResponseEntity<Map<String, Object>> solveDungeon(@RequestBody int[][] dungeonMatrix) {
        Map<String, Object> response = new HashMap<>();

        if (dungeonMatrix == null || dungeonMatrix.length == 0 || dungeonMatrix[0].length == 0) {
            response.put("error", "Invalid dungeon matrix provided.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        long startTime = System.nanoTime();

        int minimumHP = dungeonGameSolver.calculateMinimumHP(dungeonMatrix);

        long endTime = System.nanoTime();
        long durationMillis = (endTime - startTime) / 1_000_000;

        response.put("solution", minimumHP);
        response.put("executionTimeMillis", durationMillis);
        response.put("problemName", "Dungeon Game");

        try {
            String problemInputJson = objectMapper.writeValueAsString(dungeonMatrix);

            DungeonResult result = new DungeonResult();
            result.setProblemName("Dungeon Game");
            result.setProblemInputJson(problemInputJson);
            result.setSolutionOutput(minimumHP);
            result.setExecutionTimeMillis(durationMillis);
            result.setTimestamp(LocalDateTime.now());
            result.setSolutionDetails("Solved using dynamic programming approach.");

            dungeonResultRepository.save(result);
            response.put("status", "Solution calculated and saved to database.");

        } catch (JsonProcessingException e) {
            System.err.println("Error converting dungeon matrix to JSON: " + e.getMessage());
            response.put("status", "Solution calculated, but failed to save input to database (JSON conversion error).");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            System.err.println("Error saving solution to database: " + e.getMessage());
            response.put("status", "Solution calculated, but failed to save to database.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
