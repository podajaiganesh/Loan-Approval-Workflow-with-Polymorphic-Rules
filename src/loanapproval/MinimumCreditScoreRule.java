package loanapproval;

public class MinimumCreditScoreRule implements EligibilityRule {
    private int minScore;

    public MinimumCreditScoreRule(int minScore) {
        this.minScore = minScore;
    }

    @Override
    public RuleOutcome evaluate(LoanApplication application) {
        if (application.getCreditScore() >= minScore) {
            return new RuleOutcome("MinimumCreditScoreRule", RuleOutcome.Severity.PASS, "Credit score acceptable");
        } else if (application.getCreditScore() >= minScore - 30) {
            return new RuleOutcome("MinimumCreditScoreRule", RuleOutcome.Severity.SOFT_FLAG,
                    "Credit score slightly below ideal: " + application.getCreditScore());
        } else {
            return new RuleOutcome("MinimumCreditScoreRule", RuleOutcome.Severity.HARD_FAIL,
                    "Credit score too low: " + application.getCreditScore());
        }
    }
}