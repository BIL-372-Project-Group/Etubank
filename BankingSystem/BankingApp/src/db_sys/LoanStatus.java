package db_sys;

public class LoanStatus {
    private int loanStatusId; // Primary Key
    private String typeName; // Name of the loan status (e.g., Active, Closed)
    private String description; // Detailed description (Optional)

 

    public LoanStatus(int loanStatusId, String typeName, String description) {
        this.loanStatusId = loanStatusId;
        this.typeName = typeName;
        this.description = description;
    }

    public int getLoanStatusId() {
        return loanStatusId;
    }

    public void setLoanStatusId(int loanStatusId) {
        this.loanStatusId = loanStatusId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "LoanStatus{" +
                "loanStatusId=" + loanStatusId +
                ", typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
