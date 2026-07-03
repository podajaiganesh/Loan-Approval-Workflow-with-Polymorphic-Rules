package loanapproval;

public class LoanApplication {
    private String applicantName;
    private String loanType;      // "PERSONAL", "HOME", "BUSINESS"
    private double income;
    private double existingEMI;
    private double loanAmount;
    private int tenureYears;
    private int creditScore;
    private double propertyValue;   // used only for HOME loans
    private double businessTurnover; // used only for BUSINESS loans

    public LoanApplication(String applicantName, String loanType, double income,
                            double existingEMI, double loanAmount, int tenureYears,
                            int creditScore, double propertyValue, double businessTurnover) {
        this.applicantName = applicantName;
        this.loanType = loanType;
        this.income = income;
        this.existingEMI = existingEMI;
        this.loanAmount = loanAmount;
        this.tenureYears = tenureYears;
        this.creditScore = creditScore;
        this.propertyValue = propertyValue;
        this.businessTurnover = businessTurnover;
    }

    public String getApplicantName() { return applicantName; }
    public String getLoanType() { return loanType; }
    public double getIncome() { return income; }
    public double getExistingEMI() { return existingEMI; }
    public double getLoanAmount() { return loanAmount; }
    public int getTenureYears() { return tenureYears; }
    public int getCreditScore() { return creditScore; }
    public double getPropertyValue() { return propertyValue; }
    public double getBusinessTurnover() { return businessTurnover; }
}