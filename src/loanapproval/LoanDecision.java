package loanapproval;

import java.util.List;

public class LoanDecision {
    public enum Status { APPROVE, REFER, REJECT }

    private Status status;
    private List<String> reasons;

    public LoanDecision(Status status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }

    public Status getStatus() { return status; }
    public List<String> getReasons() { return reasons; }

    public void printDecision() {
        System.out.println("Decision: " + status);
        System.out.println("Reasons:");
        for (String reason : reasons) {
            System.out.println("  - " + reason);
        }
    }
}