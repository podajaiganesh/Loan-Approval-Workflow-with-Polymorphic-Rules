package loanapproval;

public class MinimumIncomeRule implements EligibilityRule {
    private double minIncome;

    public MinimumIncomeRule(double minIncome) {
        this.minIncome = minIncome;
    }

    @Override
    public RuleOutcome evaluate(LoanApplication application) {
        if (application.getIncome() >= minIncome) {
            return new RuleOutcome("MinimumIncomeRule", RuleOutcome.Severity.PASS, "Income sufficient");
        } else {
            return new RuleOutcome("MinimumIncomeRule", RuleOutcome.Severity.HARD_FAIL,
                    "Income below minimum required: " + minIncome);
        }
    }
}