package db_sys;

public class AccountType {
    private int accountTypeId; // Primary Key
    private String typeName; // Account type name (e.g., Savings, Checking, Business)
    private String description; // Detailed description (Optional)


    public AccountType(int accountTypeId, String typeName, String description) {
        this.accountTypeId = accountTypeId;
        this.typeName = typeName;
        this.description = description;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
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
        return "AccountType{" +
                "accountTypeId=" + accountTypeId +
                ", typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
