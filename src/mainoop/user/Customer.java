package mainoop.user;

// Customer kế thừa attribute và method của class User 
public class Customer extends User {
    private String customerName;    //Tên khách hàng
    private String address; //địa chỉ khách hàng
    
    //hàm tạo không tham số
    public Customer() {
        super();
        customerName = "";
        address = "";
    }
    
    //hàm tạo có tham số
    public Customer(int id, String name, String pass, String address) {
        super(id, pass);    // phương thức này sẽ gọi đến hàm tạo 2 tham số của class cha(class User)
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

    //viết lại hàm mặc định toString
    @Override public String toString() {
        return super.getUserId() + " , " + this.getcustomerName() + " , " + super.getUserPassword() + " , " + this.address;
    }

    // viết lại hàm trừu tượng của class User 
    @Override
    public boolean checkUserName(String userName) {
        return this.getcustomerName().equals(userName);
    }
    @Override
    public String getAll() {
        return (super.getUserId() + " | " + this.getcustomerName() + " | " + super.getUserPassword() + " | " + this.getAddress());
    }


}
