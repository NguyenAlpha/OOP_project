package mainoop.Shoppingcart;

import java.io.BufferedWriter;
import java.util.ArrayList;
import mainoop.product.Product;
import java.io.FileWriter;

public class ShoppingCart {
    private int customerId;
    private ArrayList<Product> listProductShoppingCart = new ArrayList<>();
    private ArrayList listQuantityProductShoppingCart = new ArrayList<>();
    private int sumPriceProduct;
    private double ProductPrice;

    public ShoppingCart() {}
    
    public ShoppingCart(int customerId, ArrayList<Product> listProductShoppingCart, ArrayList listQuantityProductShoppingCart, int sumPriceProduct, double ProductPrice) {
        this.customerId = customerId;
        this.listProductShoppingCart = listProductShoppingCart;
        this.listQuantityProductShoppingCart = listQuantityProductShoppingCart;
        this.ProductPrice = ProductPrice;
        this.sumPriceProduct = sumPriceProduct;
    }
      public double getProductPrice() {
      return ProductPrice;
   }
    public void addProduct(Product product, int quantity){
      listProductShoppingCart.add(product);
      listQuantityProductShoppingCart.add(quantity);
    
    }
   
    public void calcualateAll(){
      sumPriceProduct = 0;
      for( int i= 0; i < listProductShoppingCart.size(); i++){
        Product product = listProductShoppingCart.get(i); // Lấy sản phẩm tại vị trí i
        int quantity = (int) listQuantityProductShoppingCart.get(i); // Lấy số lượng của sản phẩm tại vị trí i
        sumPriceProduct += product.getproductPrice() * quantity; // Cộng dồn giá trị của sản phẩm vào tổng
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
              sumPriceProduct -= product.getproductPrice() * currentQuantity;
          } else {
              // Giảm số lượng
              listQuantityProductShoppingCart.set(index, currentQuantity - quantityToRemove);
              sumPriceProduct -= product.getproductPrice() * quantityToRemove;
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
                writer.write("Product: " + product.getproductName() + "ProductID: " +  product.getproductID() + "Product Price" + product.getproductPrice() + "TotalPrice " + product.getproductPrice() * quantity +"\n")
                writer.write("----------------------------------------\n");
                writer.write("Total Price: " + sumPriceProduct + "\n");
              }

            } catch (Exception e) {
              System.out.println("Error writing to file: " + e.getMessage());\
              

          }
}