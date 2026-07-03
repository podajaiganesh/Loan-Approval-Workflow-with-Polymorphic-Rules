package loanapproval;

public class InvalidLoanApplicationException extends Exception {
    public InvalidLoanApplicationException(String message) {
        super(message);
    }
}