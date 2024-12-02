package dataAccess;

public class transaction_type {
    public int transaction_type_id; // Primary Key
    public String type_name; // Name of the transaction type
    public String description; // Detailed description (Optional)

    public transaction_type type;

    public transaction_type(int transaction_type_id, String type_name, String description, transaction_type type) {
        this.transaction_type_id = transaction_type_id;
        this.type_name = type_name;
        this.description = description;
        this.type = type;
    }

    @Override
    public String toString() {
        return "transaction_type [transaction_type_id=" + transaction_type_id + ", type_name=" + type_name
                + ", description=" + description + ", type=" + type + "]";
    }
}
