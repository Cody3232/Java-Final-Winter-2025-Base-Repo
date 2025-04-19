package org.keyin.memberships;

import java.sql.SQLException;

public class MembershipService {

    private final MembershipDAO dao = new MembershipDAO();

    // Method to add a membership
    public void addMembership(Membership membership, int userId) throws SQLException {
        dao.addMembership(membership, userId);  // Pass userId to the DAO
    }

    // Method to get the total revenue from memberships
    public double getTotalRevenue() throws SQLException {
        return dao.getTotalRevenue();
    }

    public double getMemberExpenses (int userId) throws SQLException {
        return dao.getTotalExpensesByUser(userId);
    }
}
