package com.pedrohk.DungeonGameJava.repository;


import com.pedrohk.DungeonGameJava.model.DungeonResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DungeonResultRepository extends JpaRepository<DungeonResult, Long> {
}
