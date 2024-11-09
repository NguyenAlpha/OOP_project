package mainoop.shoppingcart;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import mainoop.product.Product;

public class ShoppingCart {
    private int customerId = 0; //mã khách hàng
    private ArrayList<Product> listProductShoppingCart = new ArrayList<>(); //danh sách sản phẩm trong giỏ hàng của khách hàng
    private ArrayList<Integer> listQuantityProductShoppingCart = new ArrayList<>();  //danh sách số lựng sản phẩm của mỗi món sản phẩm 
    private int sumPriceProduct = 0;  //tổng giá tiền của danh sách sản phẩm đó
    // private double ProductPrice;

    // hàm tạo không tham số
    public ShoppingCart() {}
    
    // hàm tạo có tham số
    public ShoppingCart(int customerId, ArrayList<Product> listProductShoppingCart, ArrayList<Integer> listQuantityProductShoppingCart, int sumPriceProduct, double ProductPrice) {
        this.customerId = customerId;
        this.listProductShoppingCart = listProductShoppingCart;
        this.listQuantityProductShoppingCart = listQuantityProductShoppingCart;
        // this.ProductPrice = ProductPrice;
        this.sumPriceProduct = sumPriceProduct;
    }
    
    //==================geter======================
    public int getCustomerId() {
      return customerId;
    }
    public ArrayList<Product> getListProductShoppingCart() {
      return listProductShoppingCart;
    }
    public ArrayList<Integer> getListQuantityProductShoppingCart() {
      return listQuantityProductShoppingCart;
    }
    public int getSumPriceProduct() {
      return sumPriceProduct;
    }
    
    // public double getProductPrice() {
    //     return ProductPrice;
    // }

    public void addProduct(Product product, int quantity){
        listProductShoppingCart.add(product);
        listQuantityProductShoppingCart.add(quantity);
    }
   
    public void calcualateAll(){
        sumPriceProduct = 0;
        for( int i= 0; i < listProductShoppingCart.size(); i++){
          Product product = listProductShoppingCart.get(i); // Lấy sản phẩm tại vị trí i
          int quantity = listQuantityProductShoppingCart.get(i); // Lấy số lượng của sản phẩm tại vị trí i
          sumPriceProduct += product.getProductPrice() * quantity; // Cộng dồn giá trị của sản phẩm vào tổng
        }
    }
    public void removeProduct(Product product, int quantityToRemove) {
      int index = listProductShoppingCart.indexOf(product);
      if (index != -1) {
          int currentQuantity = (int) listQuantityProductShoppingCart.get(index);
          
          if (quantityToRemove >= currentQuantity) {
              // Xóa hoàn toàn sản phẩm
              listProductShoppingCart.remove(index);
              listQuantityProductShoppingCart.remove(index);
              sumPriceProduct -= product.getProductPrice() * currentQuantity;
          } else {
              // Giảm số lượng
              listQuantityProductShoppingCart.set(index, currentQuantity - quantityToRemove);
              sumPriceProduct -= product.getProductPrice() * quantityToRemove;
          }
      }
    }
          public void writeToFile(String path){
            try{
              BufferedWriter writer = new BufferedWriter(new FileWriter("ShoppingCartlist.txt"));
              writer.write("Customer Shoppingcart\n");
              writer.write("------------------------\n");
              writer.write("Customer ID:"+ customerId +"\n");
              for(int i =0; i < listProductShoppingCart.size();i ++){
                Product product = listProductShoppingCart.get(i);
                int quantity = (int) listQuantityProductShoppingCart.get(i);
                writer.write("Product: " + product.getProductName() + "ProductID: " +  product.getProductId() + "Product Price" + product.getProductPrice() + "TotalPrice " + product.getProductPrice() * quantity +"\n");
                writer.write("----------------------------------------\n");
                writer.write("Total Price: " + sumPriceProduct + "\n");
              }

            } catch (Exception e) {
              System.out.println("Error writing to file: " + e.getMessage());
              

          }
}
}
