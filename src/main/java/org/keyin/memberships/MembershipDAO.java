package org.keyin.memberships;

import org.keyin.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MembershipDAO {

    // Adds a membership to the DB
    public void addMembership(Membership membership) throws SQLException {
        String sql = "INSERT INTO memberships (membership_type, membership_price, duration_months) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, membership.getMembershipType());
            pstmt.setDouble(2, membership.getMembershipPrice());
            pstmt.setInt(3, membership.getDurationMonths());
            pstmt.executeUpdate();
        }
    }
}
