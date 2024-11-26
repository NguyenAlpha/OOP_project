package mainoop.bill;

import mainoop.user.Customer;

public class Bill {
    private int billId; //mã bill
    private Customer customer;
    private String status;


    public Bill() {

    }

    public Bill(int billId, Customer customer, String status) {

    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

}




