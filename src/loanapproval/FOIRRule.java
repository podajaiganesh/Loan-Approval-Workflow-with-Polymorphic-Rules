package loanapproval;

public class FOIRRule implements EligibilityRule {
    private double maxFoirRatio;

    public FOIRRule(double maxFoirRatio) {
        this.maxFoirRatio = maxFoirRatio;
    }

    @Override
    public RuleOutcome evaluate(LoanApplication application) {
        double emiEstimate = application.getLoanAmount() / (application.getTenureYears() * 12.0);
        double totalEMI = application.getExistingEMI() + emiEstimate;
        double foir = totalEMI / application.getIncome();

        if (foir <= maxFoirRatio) {
            return new RuleOutcome("FOIRRule", RuleOutcome.Severity.PASS,
                    "FOIR within limit: " + String.format("%.2f", foir));
        } else {
            return new RuleOutcome("FOIRRule", RuleOutcome.Severity.HARD_FAIL,
                    "FOIR too high: " + String.format("%.2f", foir));
        }
    }
}