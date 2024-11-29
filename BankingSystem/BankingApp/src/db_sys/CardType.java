public class CardType {
    private int cardTypeId; // Primary Key
    private String typeName; // Card type name (e.g., Credit, Debit)
    private String description; // Detailed description (Optional)


        public CardType(int cardTypeId, String typeName, String description) {
        this.cardTypeId = cardTypeId;
        this.typeName = typeName;
        this.description = description;
    }

    public int getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(int cardTypeId) {
        this.cardTypeId = cardTypeId;
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
        return "CardType{" +
                "cardTypeId=" + cardTypeId +
                ", typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
