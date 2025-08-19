package com.pedrohk.Dungeon_Game_Java.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dungeon_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DungeonResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String problemInputJson;

    private int solutionOutput;

    private long executionTimeMillis;

    private String problemName;

    @Column(columnDefinition = "TEXT")
    private String solutionDetails;

    private java.time.LocalDateTime timestamp;
}