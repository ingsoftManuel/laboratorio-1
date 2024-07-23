package cue.edu.co.vote.database;

import java.sql.*;

public class VoterDAO {

    public boolean isVoterValid(String identificationNumber) throws SQLException {
        System.out.println("Checking voter ID: " + identificationNumber);
        String query = "SELECT * FROM voters WHERE identification_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, identificationNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Voter found in database: " + rs.getString("identification_number"));
                return true;
            } else {
                System.out.println("Voter not found in database.");
                return false;
            }
        }
    }

    public int getVoterId(String identificationNumber) throws SQLException {
        String query = "SELECT id FROM voters WHERE identification_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, identificationNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return -1;
            }
        }
    }

    public void insertVoter(String name, String identificationNumber) throws SQLException {
        String query = "INSERT INTO voters (name, identification_number) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.setString(2, identificationNumber);
            pstmt.executeUpdate();
            System.out.println("Voter inserted into database.");
        }
    }
}
