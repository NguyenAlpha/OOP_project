package mainoop.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import mainoop.ListInterface;

public class ProductList implements ListInterface{
    private ArrayList<Product> productList = new ArrayList<>(); //danh sách sản phẩm
    private int productCount = 0;   //số lượng sản phẩm
    private int lastProdectId = 0;   //ID sản được thêm vào cuối cùng
    // mỗi sản phẩm có Id là duy nhất.
    // khi xóa sản phẩm Id sản phẩm đó sẽ không bị thay để bởi các sản phẩm khác,
    // mà chỉ bỏ trống Id đó không dùng đến, lastProdectId để lưu Id của sản phẩm cuối thêm vào.
    // khi tất cả danh sách sản phẩn bị xóa thì Id sản phẩm mới vẫn là mới nhất
    // đảm bảo cho giỏ hàng của khách hàng không bị lỗi sản phẩm khi id sản phẩm bị ghi đè
    
    public ProductList() {}

    public ProductList(String path) {  //đường dẫn đến file để nhập danh sách
        addFromFile(path);
    }
    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    @Override
    public void addFromFile(String path) {
        try {
            File read = new File(path); //kết nối file muốn đọc vào class file
            try (Scanner reader = new Scanner(read) //sử dụng scanner để đọc luồng file ở trên
            ) {
                lastProdectId = Integer.parseInt(reader.nextLine());
                while (reader.hasNextLine()) {  //khi file cần đọc vẫn còn dòng
                    productCount++;
                    String s = reader.nextLine();   //đọc dòng đó
                    String[] sSplit = s.split("[ ]*[|][ ]*");    //tách các thành phần cần lấy
                    
                    //lưu vào dữ liệu danh sách sản phẩm
                    //phân tích string thành long https://docs.oracle.com/javase/8/docs/api/java/lang/Long.html
                    Product product = new Product(Integer.parseInt(sSplit[0]), sSplit[1], Long.parseLong(sSplit[2]), Integer.parseInt(sSplit[3]));
                    productList.add(product);
                    
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter("src/mainoop/data/product.txt");
            writer.write(String.valueOf(lastProdectId));
            for(Product product : productList) {
                writer.write("\n" + product.getAll());
            }
            writer.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // số lượng các loại sản phẩm
    public int getProductCount() {
        return productCount;
    }

    // in ra danh sách các sản phẩm
    public void viewProductsList() {
        for (Product product : productList) {
            viewAlignedProductList(product);
        }
    }

    public void viewAlignedProductList(Product product) {
        System.out.print(product.getProductId());   //xuất id sản phẩm
            int temp = 4 - String.valueOf(product.getProductId()).length(); //chuyển id sang String và lấy đội dài của String id
            while(temp > 0) {
                System.out.print(" ");
                temp--;
            }
            System.out.print("|");

            System.out.print(product.getProductName());
            temp = 30 - product.getProductName().length();
            while(temp > 0) {
                System.out.print(" ");
                temp--;
            }
            System.out.print("|");

            System.out.print(product.getProductPrice());
            temp = 20 - String.valueOf(product.getProductPrice()).length();
            while(temp > 0) {
                System.out.print(" ");
                temp--;
            }
            System.out.print("|");

            System.out.println(product.getProductQuantity());
    }

    // Tìm sản phẩm 
    public void searchProduct() {
        System.out.println("==================MENU TÌM KIẾM=======================");
        System.out.println("1. Tìm theo tên sản phẩm");
        System.out.println("2. Tìm theo khoản giá sản phẩm");
        System.out.println("==============================================");

        System.out.print("Nhập thao tác: ");
        try (Scanner scanner = new Scanner(System.in)) {
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
                        viewAlignedProductList(product);
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
                        viewAlignedProductList(product);
                    }
                }
            }
        }
    }

    public void addProduct(String productName, long productPrice, int productQuantity) {
        productCount++;
        lastProdectId++;
        Product newProduct = new Product(lastProdectId, productName, productPrice, productQuantity);
        productList.add(newProduct);
        newProduct.addProduct(newProduct);
        writeToFile();
    }   

    // Tìm sản phẩm từ Id
    public Product searchProductById(int id) {
        for(Product product : productList) {
            if(product.getProductId() == id)
                return product;
        }
        return null;
    }

    // Tìm sản phẩm từ tên sản phẩm
    public Product getProductByName(String name) {
        for(Product product : productList) {
            if(product.getProductName() == name)
                return product;
        }
        return null;
    }

    // trả về vị trí sản phẩm đó trong danh sách
    public int location(Product product) {
        int i = 0;
        for(Product p : productList) {
            if(p == product) {
                return i;
            }
            i++;
        }
        return -1;
    }

    // Xóa sản phẩm
    public void RemoveProduct(Product product) {
        if(product == null)
        {
            System.out.println("Sản phẩm này không tồn tại.");
            return;
        }
        productList.remove(location(product));
        writeToFile();
        System.out.println("Xóa thành công.");

    }

    
}