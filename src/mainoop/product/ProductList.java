package mainoop.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductList{
    private int productCount = 0;
    private ArrayList<Product> productList = new ArrayList<>();
    
    public ProductList() {}

    public ProductList(String path) {  //đường dẫn đến file để nhập danh sách
        addFromFile(path);
    }
    
    public void addFromFile(String path) {
        try {
            File read = new File(path); //kết nối file muốn đọc vào class file
            Scanner reader = new Scanner(read); //sử dụng scanner để đọc luồng file ở trên
            
            while (reader.hasNextLine()) {  //khi file cần đọc vẫn còn dòng
                productCount++; //Tăng số lượng sản phẩm
                String s = reader.nextLine();   //đọc dòng đó
                String[] sSplit = s.split("[ ]*[|][ ]*");    //tách các thành phần cần lấy

                //lưu vào dữ liệu danh sách sản phẩm
                //phân tích string thành long https://docs.oracle.com/javase/8/docs/api/java/lang/Long.html
                Product product = new Product(Integer.parseInt(sSplit[0]), sSplit[1], sSplit[2], Long.parseLong(sSplit[3]), Integer.parseInt(sSplit[4]));
                productList.add(product);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // số lượng các loại sản phẩm
    public int getProductCount() {
        return productCount;
    }

    // in ra danh sách các sản phẩm
    public void viewProductsList() {
        for (Product product : productList) {
            System.out.println(product);
        }
        System.out.println("Total products: " + getProductCount());
    }

    // Tìm sản phẩm 
    public void searchProduct() {
        System.out.println("==================MENU TÌM KIẾM=======================");
        System.out.println("1. Tìm theo tên sản phẩm");
        System.out.println("2. Tìm theo khoản giá sản phẩm");
        System.out.println("==============================================");

        System.out.print("Nhập thao tác: ");
        Scanner scanner = new Scanner(System.in);
        int search = scanner.nextInt();
        scanner.nextLine();

        while((search < 1) || (search > 2)) {
            System.out.print("Thao tác không đúng.\nNhập thao tác: ");
            search = scanner.nextInt();
            scanner.nextLine();
        }

        if(search == 1) {
            System.out.print("Nhập tên sản phẩm cần tìm: ");
            String searchProductName = scanner.nextLine();
            for (Product product : productList) {
                if(product.getProductName().toLowerCase().contains(searchProductName.toLowerCase())) {
                    System.out.println(product);
                }
            }

        }

        if(search == 2) {
            System.out.print("Nhập tên sản phẩm cần tìm: ");
            String searchProductName = scanner.nextLine();
            System.out.print("Nhập khoản giá thấp nhất cần tìm: ");
            long lowestPrice = scanner.nextLong();
            scanner.nextLine();
            System.out.print("Nhập khoản giá cao nhất cần tìm: ");
            long highestPrice = scanner.nextLong();
            scanner.nextLine();

            for (Product product : productList) {
                if(product.getProductName().toLowerCase().contains(searchProductName.toLowerCase()) && (product.getProductPrice() >= lowestPrice) && (product.getProductPrice() <= highestPrice)) {
                    System.out.println(product);
                }
            }
        }

        scanner.close();
    }
    
}