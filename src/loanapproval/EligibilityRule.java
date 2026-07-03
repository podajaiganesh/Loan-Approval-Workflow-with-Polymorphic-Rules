package loanapproval;

public interface EligibilityRule {
    RuleOutcome evaluate(LoanApplication application);
}