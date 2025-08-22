package com.pedrohk.Dungeon_Game_Java;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;


import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class DungeonGameSimulation extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .contentTypeHeader("application/json");

    private ChainBuilder solveDungeonRequest =
            exec(
                    http("Solve Dungeon Game Request")
                            .post("/api/dungeon/solve")
                            .body(StringBody("[\n" +
                                    "        [-2, -3, 3],\n" +
                                    "        [-5, -10, 1],\n" +
                                    "        [10, 30, -5]\n" +
                                    "      ]"))
                            .asJson()
                            .check(status().is(200))
            );

    private ScenarioBuilder scn = scenario("Dungeon Game Solving Scenario")
            .exec(solveDungeonRequest);

    {
        setUp(
                scn.injectOpen(
                        rampUsers(100).during(Duration.ofSeconds(10))
                )
        ).protocols(httpProtocol);
    }
}
