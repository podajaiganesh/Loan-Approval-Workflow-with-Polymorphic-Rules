package loanapproval;

public class PropertyValuationRule implements EligibilityRule {
    private double maxLoanToValueRatio;

    public PropertyValuationRule(double maxLoanToValueRatio) {
        this.maxLoanToValueRatio = maxLoanToValueRatio;
    }

    @Override
    public RuleOutcome evaluate(LoanApplication application) {
        double ltv = application.getLoanAmount() / application.getPropertyValue();

        if (ltv <= maxLoanToValueRatio) {
            return new RuleOutcome("PropertyValuationRule", RuleOutcome.Severity.PASS,
                    "Loan-to-value ratio acceptable: " + String.format("%.2f", ltv));
        } else {
            return new RuleOutcome("PropertyValuationRule", RuleOutcome.Severity.HARD_FAIL,
                    "Loan-to-value ratio too high: " + String.format("%.2f", ltv));
        }
    }
}