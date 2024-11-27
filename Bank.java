package db_sys;

public class Bank {
    private int bank_id;
    private String name;
    private String address;
    private String contact_number;
    private String branch_code;

    public Bank(int bank_id, String name, String address, String contact_number, String branch_code) {
        this.bank_id = bank_id;
        this.name = name;
        this.address = address;
        this.contact_number = contact_number;
        this.branch_code = branch_code;
    }

    public int getBank_id() {
        return bank_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact_number() {
        return contact_number;
    }

    public String getBranch_code() {
        return branch_code;
    }

}
