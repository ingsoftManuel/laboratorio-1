package cue.edu.co.vote.database;

import cue.edu.co.vote.model.Vote;

import java.sql.*;

public class VoteDAO {

    public boolean hasVoted(int voterId, String jornada) throws SQLException {
        String query = "SELECT * FROM votes WHERE voter_id = ? AND jornada = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, voterId);
            pstmt.setString(2, jornada);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Voter has already voted in this jornada.");
                return true;
            } else {
                System.out.println("Voter has not voted in this jornada.");
                return false;
            }
        }
    }

    public void insertVote(Vote vote) throws SQLException {
        String query = "INSERT INTO votes (voter_id, candidate_id, jornada) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, vote.getVoterId());
            pstmt.setInt(2, vote.getCandidateId());
            pstmt.setString(3, vote.getJornada());
            pstmt.executeUpdate();
            System.out.println("Vote inserted into database.");
        }
    }
}
