package com.pedrohk.DungeonGameJava.model;

public class DungeonResponse {
    private String problemName;
    private Integer solution;
    private Long executionTimeMillis;
    private String status;
    private String error;

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public Integer getSolution() {
        return solution;
    }

    public void setSolution(Integer solution) {
        this.solution = solution;
    }

    public Long getExecutionTimeMillis() {
        return executionTimeMillis;
    }

    public void setExecutionTimeMillis(Long executionTimeMillis) {
        this.executionTimeMillis = executionTimeMillis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
