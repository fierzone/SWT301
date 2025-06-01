package org.example;

public class InsuranceClaim {

    private String claimId;
    private double amount;
    private String claimStatus;

    public InsuranceClaim(String claimId, double amount) {
        if (claimId == null || claimId.isEmpty()) {
            throw new IllegalArgumentException("Claim ID cannot be null or empty");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.claimId = claimId;
        this.amount = amount;
        this.claimStatus = "Pending";
    }

    public String getClaimId() {
        return claimId;
    }

    public double getAmount() {
        return amount;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public boolean processClaim(String newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Claim status cannot be null");
        }
        if (!"Pending".equalsIgnoreCase(this.claimStatus)) {
            return false;
        }
        this.claimStatus = newStatus;
        return true;
    }

    public double calculatePayout() {
        if ("Approved".equalsIgnoreCase(this.claimStatus)) {
            return amount * 0.85;
        }
        return 0;
    }

    public void updateClaimAmount(double newAmount) {
        if (newAmount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.amount = newAmount;
    }

    @Override
    public String toString() {
        return "InsuranceClaim{" +
                "claimId='" + claimId + '\'' +
                ", amount=" + amount +
                ", claimStatus='" + claimStatus + '\'' +
                '}';
    }
}