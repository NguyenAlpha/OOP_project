package mainoop.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import mainoop.AddAndRemove;

public class ProductList extends AddAndRemove{
    private static int productCount = 0;
    private ArrayList<product> productList = new ArrayList<>();
    
    public ProductList() {}

    public ProductList(String path) {  //đường dẫn đến file để nhập danh sách
        addFromFile(path);
    }
    
    public void addFromFile(String path) {
        try {
            File read = new File(path); //kết nối file muốn đọc vào class file
            Scanner reader = new Scanner(read); //sử dụng scanner để đọc luồng file ở trên
            
            while (reader.hasNextLine()) {  //khi file cần đọc vẫn còn dòng
                String s = reader.nextLine();   //đọc dòng đó
                String[] sSplit = s.split("[ ]*[|][ ]*");    //tách các thành phần cần lấy

                //lưu vào dữ liệu danh sách sản phẩm
                //phân tích string thành long https://docs.oracle.com/javase/8/docs/api/java/lang/Long.html
                product pd = new product(Integer.parseInt(sSplit[0]), sSplit[1], sSplit[2], Long.parseLong(sSplit[3]));
                productList.add(pd);
                productCount++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // public boolean isProductListEmpty() {
    //     return productList.isEmpty();
    // } 
 
    public static int getProductCount() {
        return productCount;
    }

    public void printProducts() {
        for (product p : productList) {
            System.out.println(p);
        }
        System.out.println("Total products: " + getProductCount());
    }

    @Override
    public void addProduct(int productID, String productName, String productType, long productPrice) {
        product p = new product(productID, productName, productType, productPrice);
        productList.add(p);
    }

    @Override
    public void removeProduct() {
        
    }
}