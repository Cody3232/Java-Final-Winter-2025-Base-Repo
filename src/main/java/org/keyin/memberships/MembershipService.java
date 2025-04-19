package org.keyin.memberships;

import java.util.List;
import java.sql.SQLException;
// Service class for membership handle all the business logic
// and only uses the DAO to interact with the database it does not have methods to do so
// you can inject in your dao to use in your service. An example will be in the code
public class MembershipService {

    private final MembershipDAO dao = new MembershipDAO();

    public void addMembership (Membership membership) throws SQLException {
        dao.addMembership(membership);
    }
    // When you inject in the DAO you have access to all methods in it

}
