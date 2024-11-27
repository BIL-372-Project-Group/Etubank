package db_sys;

public class LoanType {
    private int loanTypeId; // Primary Key
    private String typeName; // Type of loan (e.g., Home, Car)
    private String description; // Detailed description


    public LoanType(int loanTypeId, String typeName, String description) {
        this.loanTypeId = loanTypeId;
        this.typeName = typeName;
        this.description = description;
    }

    public int getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(int loanTypeId) {
        this.loanTypeId = loanTypeId;
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
        return "LoanType{" +
                "loanTypeId=" + loanTypeId +
                ", typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
