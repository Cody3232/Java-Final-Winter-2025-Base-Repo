package org.keyin.memberships;

import org.keyin.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MembershipDAO {

// Adds a membership to the DB and links it to a user
    public void addMembership(Membership membership, int userId) throws SQLException {
        String sql = "INSERT INTO memberships (membership_type, membership_price, duration_months, user_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, membership.getMembershipType());
            pstmt.setDouble(2, membership.getMembershipPrice());
            pstmt.setInt(3, membership.getDurationMonths());
            pstmt.setInt(4, userId);
            pstmt.executeUpdate();
        }
    }

// Fetches the total revenue from memberships in the DB
    public double getTotalRevenue() throws SQLException {
        String sql = "SELECT SUM(membership_price) AS total_revenue FROM memberships";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("total_revenue");
            } else {
                return 0.0;
        }
    }
}

// View total amount
    public double getTotalExpensesByUser(int userId) throws SQLException {
        String sql = "SELECT COALESCE(SUM(membership_price), 0) FROM memberships WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getDouble(1);
                    }
                    return 0.0;
                }
            }
    }
}