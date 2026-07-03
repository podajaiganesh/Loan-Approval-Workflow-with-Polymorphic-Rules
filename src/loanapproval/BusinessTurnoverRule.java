package loanapproval;

public class BusinessTurnoverRule implements EligibilityRule {
    private double minTurnoverMultiple;

    public BusinessTurnoverRule(double minTurnoverMultiple) {
        this.minTurnoverMultiple = minTurnoverMultiple;
    }

    @Override
    public RuleOutcome evaluate(LoanApplication application) {
        double requiredTurnover = application.getLoanAmount() * minTurnoverMultiple;

        if (application.getBusinessTurnover() >= requiredTurnover) {
            return new RuleOutcome("BusinessTurnoverRule", RuleOutcome.Severity.PASS,
                    "Turnover sufficient for loan amount");
        } else {
            return new RuleOutcome("BusinessTurnoverRule", RuleOutcome.Severity.HARD_FAIL,
                    "Turnover insufficient for requested loan amount");
        }
    }
}