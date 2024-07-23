package cue.edu.co.vote.database;

import cue.edu.co.vote.model.Candidate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO {

    public List<Candidate> getAllCandidates() throws SQLException {
        List<Candidate> candidates = new ArrayList<>();
        String query = "SELECT * FROM candidates";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Candidate candidate = new Candidate(rs.getInt("id"), rs.getString("name"), rs.getString("photo_path"), rs.getString("jornada"));
                candidates.add(candidate);
            }
        }
        return candidates;
    }


}
