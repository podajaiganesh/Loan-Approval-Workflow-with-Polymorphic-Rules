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
            if (name == null || name.trim().isEmpty()) {
                throw new InvalidLoanApplicationException("Applicant name cannot be empty.");
            }

            System.out.print("Enter loan type (PERSONAL / HOME / BUSINESS): ");
            String loanType = sc.nextLine().trim().toUpperCase();
            if (!loanType.equals("PERSONAL") && !loanType.equals("HOME") && !loanType.equals("BUSINESS")) {
                throw new InvalidLoanApplicationException("Loan type must be PERSONAL, HOME, or BUSINESS.");
            }

            if (!loanType.equals("PERSONAL") && !loanType.equals("HOME") && !loanType.equals("BUSINESS")) {
                throw new InvalidLoanApplicationException("Loan type must be PERSONAL, HOME, or BUSINESS.");
            }

            System.out.print("Enter monthly income: ");
            double income = sc.nextDouble();
            if (income < 0) {
                throw new InvalidLoanApplicationException("Income cannot be negative.");
            }

            System.out.print("Enter existing EMI: ");
            double existingEMI = sc.nextDouble();
            if (existingEMI < 0) {
                throw new InvalidLoanApplicationException("Existing EMI cannot be negative.");
            }

            System.out.print("Enter loan amount requested: ");
            double loanAmount = sc.nextDouble();
            if (loanAmount <= 0) {
                throw new InvalidLoanApplicationException("Loan amount must be greater than zero.");
            }

            System.out.print("Enter tenure (years): ");
            int tenure = sc.nextInt();
            if (tenure <= 0) {
                throw new InvalidLoanApplicationException("Tenure must be greater than zero.");
            }

            System.out.print("Enter credit score: ");
            int creditScore = sc.nextInt();
            if (creditScore < 300 || creditScore > 900) {
                throw new InvalidLoanApplicationException("Credit score must be between 300 and 900.");
            }

            double propertyValue = 0;
            double businessTurnover = 0;

            if (loanType.equals("HOME")) {
                System.out.print("Enter property value: ");
                propertyValue = sc.nextDouble();
                if (propertyValue <= 0) {
                    throw new InvalidLoanApplicationException("Property value must be greater than zero.");
                }
            } else if (loanType.equals("BUSINESS")) {
                System.out.print("Enter business annual turnover: ");
                businessTurnover = sc.nextDouble();
                if (businessTurnover < 0) {
                    throw new InvalidLoanApplicationException("Business turnover cannot be negative.");
                }
            }

            LoanApplication application = new LoanApplication(
                    name, loanType, income, existingEMI, loanAmount, tenure,
                    creditScore, propertyValue, businessTurnover);

            System.out.println("\n--- Evaluating Application for " + name + " ---");
            engine.evaluate(application).printDecision();

        } catch (InputMismatchException e) {
            System.out.println("\nInvalid input: please enter numbers where numbers are expected.");
        } catch (InvalidLoanApplicationException e) {
            System.out.println("\nInvalid entry: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}