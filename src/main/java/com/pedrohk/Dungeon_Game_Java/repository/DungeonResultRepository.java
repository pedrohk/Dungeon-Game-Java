package com.pedrohk.Dungeon_Game_Java.repository;


import com.pedrohk.Dungeon_Game_Java.model.DungeonResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DungeonResultRepository extends JpaRepository<DungeonResult, Long> {
}
