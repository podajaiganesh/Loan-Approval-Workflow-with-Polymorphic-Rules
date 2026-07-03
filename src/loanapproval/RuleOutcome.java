package loanapproval;

public class RuleOutcome {
    public enum Severity { PASS, SOFT_FLAG, HARD_FAIL }

    private String ruleName;
    private Severity severity;
    private String reason;

    public RuleOutcome(String ruleName, Severity severity, String reason) {
        this.ruleName = ruleName;
        this.severity = severity;
        this.reason = reason;
    }

    public String getRuleName() { return ruleName; }
    public Severity getSeverity() { return severity; }
    public String getReason() { return reason; }

    public boolean isPass() { return severity == Severity.PASS; }
}