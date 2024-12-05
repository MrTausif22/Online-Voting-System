package com.model;

public class Vote {
    private int id;
    private int userId;
    private int candidateId;

    public Vote() {}

    public Vote(int id, int userId, int candidateId) {
        this.id = id;
        this.userId = userId;
        this.candidateId = candidateId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }
}