package mainoop.Payment;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import mainoop.product.Product;
import mainoop.user.Admin;
import mainoop.user.Customer;
public class payment {
   private int Total; //Tổng tiền
   // private int productId; //Mã sản phẩm 
   private Customer  customer; //Tên khách hàng 
   private Admin admin;
   private Map<Product, Integer> cartItems = new HashMap<>();// Danh sạch giỏ hàng 
       //==================geter======================
    public Customer getcustomer() {
        return this.customer;
    }
     public Admin getadmin()
     {
      return  this.admin;
     }
     public int Total()
     {
      return this.Total;
     }
     public Map<Product, Integer> getcartItems()
     {
         return this.cartItems;
     }
    //  ------------------seter-------------------
     public void setcustomer(Customer customer)
     {
      this.customer = customer;
     }
     public void setadmin(Admin admin)
     {
      this.admin = admin;
     }

   //   ---------Hóa Đơn----------
     public void bill()
     {
       Scanner scanner = new Scanner(System.in);
       System.out.println("Xin chào, " + customer.getCustomerName());
       System.out.println("=========================HÓA ĐƠN=====================");
         System.out.println("Tên khách hàng : " + customer.getCustomerName());
       System.out.println("Danh sách sản phẩm ");  
       int grandtotal = 0;
       for(Map.Entry<Product, Integer> entry : cartItems.entrySet())
       {
        Product product = entry.getKey();
        int newQuantity = entry.getValue(); //Tính Tổng số lượng từng sản phẩm
        // int productTotal =  product.getProductPrice() *newQuantity; 
       }
     } 
}

