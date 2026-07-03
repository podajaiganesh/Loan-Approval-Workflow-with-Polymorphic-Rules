package loanapproval;

import java.util.ArrayList;
import java.util.List;

public class LoanDecisionEngine {

    public LoanDecision evaluate(LoanApplication application) {
        List<EligibilityRule> rules = LoanRuleSetFactory.getRulesFor(application.getLoanType());
        List<RuleOutcome> outcomes = new ArrayList<>();

        for (EligibilityRule rule : rules) {
            outcomes.add(rule.evaluate(application));
        }

        boolean hasHardFail = false;
        boolean hasSoftFlag = false;
        List<String> reasons = new ArrayList<>();

        for (RuleOutcome outcome : outcomes) {
            if (outcome.getSeverity() == RuleOutcome.Severity.HARD_FAIL) {
                hasHardFail = true;
                reasons.add(outcome.getReason());
            } else if (outcome.getSeverity() == RuleOutcome.Severity.SOFT_FLAG) {
                hasSoftFlag = true;
                reasons.add(outcome.getReason());
            } else {
                reasons.add(outcome.getRuleName() + ": " + outcome.getReason());
            }
        }

        LoanDecision.Status status;
        if (hasHardFail) {
            status = LoanDecision.Status.REJECT;
        } else if (hasSoftFlag) {
            status = LoanDecision.Status.REFER;
        } else {
            status = LoanDecision.Status.APPROVE;
        }

        return new LoanDecision(status, reasons);
    }
}