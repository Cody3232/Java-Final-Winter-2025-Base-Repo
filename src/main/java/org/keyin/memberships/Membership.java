package org.keyin.memberships;

//*\
// This is class file that represents a membership
//
public class Membership {
    private int membershipId;
    private String membershipType;
    private double membershipPrice;
    private int durationMonths;
    
// Default Constructor
    public Membership (){
    }

    public Membership(String membershipType, double membershipPrice, int durationMonths) {
        this.membershipType = membershipType;
        this.membershipPrice = membershipPrice;
        this.durationMonths = durationMonths;
    }

    public Membership(int membershipId, String membershipType, double membershipPrice, int durationMonths) {
        this.membershipId = membershipId;
        this.membershipType = membershipType; 
        this.membershipPrice = membershipPrice;
        this.durationMonths = durationMonths;
    }

// Getters/Setters
public int getMembershipId() {
    return membershipId;
} // Clean up code time available

public void setMembershipId(int membershipId) {
    this.membershipId = membershipId;
}

public String getMembershipType() {
    return membershipType;
}

public void setMembershipType(String membershipType) {
    this.membershipType = membershipType;
}

public double getMembershipPrice() {
    return membershipPrice;
}

public void setMembershipPrice(double membershipPrice) {
    this.membershipPrice = membershipPrice;
}

public int getDurationMonths() {
    return durationMonths;
}

public void setDurationMonths(int durationMonths) {
    this.durationMonths = durationMonths;
}
}
