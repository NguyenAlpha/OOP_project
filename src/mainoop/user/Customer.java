package mainoop.user;

public class Customer extends User {
    private String customerName;
    private String address;
    
    //hàm tạo 
    public Customer() {
        super();
        customerName = "";
        address = "";
    }
    
    //hàm tạo có tham số
    public Customer(int id, String name, String pass, String address) {
        super(id, pass);
        this.customerName = name;
        this.address = address;
    }

    //==================geter======================
    public String getcustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    //==================seter======================
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getAll() {
        return (super.getUserId() + " | " + this.getcustomerName() + " | " + super.getUserPassword() + " | " + this.getAddress());
    }

    //viết lại hàm mặc định toString
    @Override public String toString() {
        return super.getUserId() + " , " + this.getcustomerName() + " , " + super.getUserPassword() + " , " + this.address;
    }

    //viết lại class trừu tượng của class User 
    @Override
    public boolean checkUserName(String userName) {
        return this.getcustomerName().equals(userName);
    }


}
