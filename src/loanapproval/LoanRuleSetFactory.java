package loanapproval;

import java.util.ArrayList;
import java.util.List;

public class LoanRuleSetFactory {

    public static List<EligibilityRule> getRulesFor(String loanType) {
        List<EligibilityRule> rules = new ArrayList<>();

        rules.add(new MinimumCreditScoreRule(650));

        switch (loanType.toUpperCase()) {
            case "PERSONAL":
                rules.add(new MinimumIncomeRule(20000));
                rules.add(new FOIRRule(0.5));
                break;
            case "HOME":
                rules.add(new MinimumIncomeRule(30000));
                rules.add(new PropertyValuationRule(0.8));
                break;
            case "BUSINESS":
                rules.add(new BusinessTurnoverRule(3.0));
                break;
            default:
                throw new IllegalArgumentException("Unknown loan type: " + loanType);
        }

        return rules;
    }
}