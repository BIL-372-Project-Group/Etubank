package db_sys;

public class TransactionType {
    private int transactionTypeId; // Primary Key
    private String typeName; // Name of the transaction type
    private String description; // Detailed description (Optional)

    public TransactionType(int transactionTypeId, String typeName, String description) {
        this.transactionTypeId = transactionTypeId;
        this.typeName = typeName;
        this.description = description;
    }

    public int getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
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
        return "TransactionType{" +
                "transactionTypeId=" + transactionTypeId +
                ", typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
