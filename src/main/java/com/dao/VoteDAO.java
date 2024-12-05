package com.dao;

import java.sql.*;

import com.model.Vote;

public class VoteDAO {
    private Connection connection;

    public VoteDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean castVote(Vote vote) throws SQLException {
        String sql = "INSERT INTO votes (user_id, candidate_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, vote.getUserId());
            statement.setInt(2, vote.getCandidateId());
            return statement.executeUpdate() > 0;
        }
    }
}
