package com.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.model.Candidate;

public class CandidateDAO {
    private Connection connection;

    public CandidateDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addCandidate(Candidate candidate) throws SQLException {
        String sql = "INSERT INTO candidates (name, party) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, candidate.getName());
            statement.setString(2, candidate.getParty());
            return statement.executeUpdate() > 0;
        }
    }

    public List<Candidate> getAllCandidates() throws SQLException {
        String sql = "SELECT * FROM candidates";
        List<Candidate> candidates = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                candidates.add(new Candidate(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("party")
                ));
            }
        }
        return candidates;
    }
}
