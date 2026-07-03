package loanapproval;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoanDecisionEngine engine = new LoanDecisionEngine();

        try {
            System.out.println("=== Loan Approval System ===");
            System.out.print("Enter applicant name: ");
            String name = sc.nextLine();

            System.out.print("Enter loan type (PERSONAL / HOME / BUSINESS): ");
            String loanType = sc.nextLine().trim().toUpperCase();

            if (!loanType.equals("PERSONAL") && !loanType.equals("HOME") && !loanType.equals("BUSINESS")) {
                throw new InvalidLoanApplicationException("Loan type must be PERSONAL, HOME, or BUSINESS.");
            }

            System.out.print("Enter monthly income: ");
            double income = sc.nextDouble();

            System.out.print("Enter existing EMI: ");
            double existingEMI = sc.nextDouble();

            System.out.print("Enter loan amount requested: ");
            double loanAmount = sc.nextDouble();

            System.out.print("Enter tenure (years): ");
            int tenure = sc.nextInt();

            System.out.print("Enter credit score: ");
            int creditScore = sc.nextInt();

            double propertyValue = 0;
            double businessTurnover = 0;

            if (loanType.equals("HOME")) {
                System.out.print("Enter property value: ");
                propertyValue = sc.nextDouble();
            } else if (loanType.equals("BUSINESS")) {
                System.out.print("Enter business annual turnover: ");
                businessTurnover = sc.nextDouble();
            }

            validate(name, loanType, income, loanAmount, tenure, creditScore);

            LoanApplication application = new LoanApplication(
                    name, loanType, income, existingEMI, loanAmount, tenure,
                    creditScore, propertyValue, businessTurnover);

            System.out.println("\n--- Evaluating Application for " + name + " ---");
            engine.evaluate(application).printDecision();

        } catch (InputMismatchException e) {
            System.out.println("\nInvalid input: please enter numbers where numbers are expected.");
        } catch (InvalidLoanApplicationException e) {
            System.out.println("\nApplication rejected before evaluation: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static void validate(String name, String loanType, double income,
                                  double loanAmount, int tenure, int creditScore)
            throws InvalidLoanApplicationException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidLoanApplicationException("Applicant name cannot be empty.");
        }
        if (!loanType.equals("PERSONAL") && !loanType.equals("HOME") && !loanType.equals("BUSINESS")) {
            throw new InvalidLoanApplicationException("Loan type must be PERSONAL, HOME, or BUSINESS.");
        }
        if (income < 0) {
            throw new InvalidLoanApplicationException("Income cannot be negative.");
        }
        if (loanAmount <= 0) {
            throw new InvalidLoanApplicationException("Loan amount must be greater than zero.");
        }
        if (tenure <= 0) {
            throw new InvalidLoanApplicationException("Tenure must be greater than zero.");
        }
        if (creditScore < 300 || creditScore > 900) {
            throw new InvalidLoanApplicationException("Credit score must be between 300 and 900.");
        }
    }
}