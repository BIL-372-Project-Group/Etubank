package db_sys;


public class Bank {
    private int bankId; 
    private String name; 
    private String address; 
    private String contactNumber; 
    private String branchCode; 

  

    public Bank(int bankId, String name, String address, String contactNumber, String branchCode) {
        this.bankId = bankId;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.branchCode = branchCode;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankId=" + bankId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", branchCode='" + branchCode + '\'' +
                '}';
    }
}
