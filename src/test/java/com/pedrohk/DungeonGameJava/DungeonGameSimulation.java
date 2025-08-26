package com.pedrohk.DungeonGameJava;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.csv;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.core.OpenInjectionStep.atOnceUsers;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class DungeonGameSimulation extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .contentTypeHeader("application/json")
            .acceptHeader("application/json");

    private FeederBuilder<String> csvFeeder = csv("data/dungeon_matrices.csv").circular();

    private ChainBuilder solveDungeonDynamicRequest =
            exec(
                    http("Solve Dungeon Game Dynamic Request - #{dungeonMatrix}")
                            .post("/api/dungeon/solve")
                            .body(StringBody("#{dungeonMatrix}"))
                            .asJson()
                            .check(status().is(200))
                            .check(jsonPath("$.solution").exists())
                            .check(jsonPath("$.executionTimeMillis").exists())
            );

    private ChainBuilder solveComplexDungeonStaticRequest =
            exec(
                    http("Solve Complex Dungeon Game Static Request")
                            .post("/api/dungeon/solve")
                            .body(StringBody("""
                                      [
                                        [1, -2, -3, 1],
                                        [-4, -5, -6, 2],
                                        [7, 8, -9, -10],
                                        [11, -12, -13, -14]
                                      ]
                                    """)).asJson()
                            .check(status().is(200))
                            .check(jsonPath("$.solution").is("10"))
            );

    private ScenarioBuilder basicLoadScenario = scenario("Basic Load with Dynamic Dungeon Matrices")
            .feed(csvFeeder)
            .exec(solveDungeonDynamicRequest);

    private ScenarioBuilder highConcurrencyScenario = scenario("High Concurrency for Complex Dungeon")
            .exec(solveComplexDungeonStaticRequest);

    private ScenarioBuilder gradualRampUpScenario = scenario("Gradual Ramp-Up with Dynamic Data and Pause")
            .feed(csvFeeder)
            .exec(solveDungeonDynamicRequest)
            .pause(Duration.ofSeconds(1));

    {
        setUp(
                basicLoadScenario.injectOpen(rampUsers(500).during(Duration.ofSeconds(60))).protocols(httpProtocol),

                highConcurrencyScenario.injectOpen(atOnceUsers(50)).protocols(httpProtocol),

                gradualRampUpScenario.injectOpen(rampUsers(200).during(Duration.ofSeconds(120))).protocols(httpProtocol)
        );
    }
}

