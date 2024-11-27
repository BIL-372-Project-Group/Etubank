package db_sys;

public class AccountStatus {
    private int accountStatusId; // Primary Key
    private String typeName; // Status type (e.g., Active, Closed)
    private String description; // Detailed description (Optional)


    public AccountStatus(int accountStatusId, String typeName, String description) {
        this.accountStatusId = accountStatusId;
        this.typeName = typeName;
        this.description = description;
    }

    public int getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(int accountStatusId) {
        this.accountStatusId = accountStatusId;
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
        return "AccountStatus{" +
                "accountStatusId=" + accountStatusId +
                ", typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
