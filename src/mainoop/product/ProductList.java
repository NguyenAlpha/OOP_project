package mainoop.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import mainoop.FilePaths;
import mainoop.ListInterface;

public class ProductList implements ListInterface{
    private ArrayList<Product> productList = new ArrayList<>(); //danh sách sản phẩm
    private int productCount = 0;   //số lượng sản phẩm
    private int lastProdectId = 0;   //ID sản được thêm vào cuối cùng
    // mỗi sản phẩm có Id là duy nhất.
    // khi xóa sản phẩm Id sản phẩm đó sẽ không bị thay để bởi các sản phẩm khác,
    // mà chỉ bỏ trống Id đó không dùng đến, lastProdectId để lưu Id của sản phẩm cuối thêm vào.
    // khi tất cả danh sách sản phẩn bị xóa thì Id sản phẩm mới vẫn là mới nhất
    // đảm bảo cho giỏ hàng của khách hàng không bị lỗi sản phẩm khi id sản phẩm không bị ghi đè

    public ProductList() {}

    public ProductList(String path) {  //đường dẫn đến file để nhập danh sách
        addFromFile(path);
    }

    //===============================getter============================
    public int getProductCount() {
        return productCount;
    }
    public ArrayList<Product> getProductList() {
        return productList;
    }

    //===============================setter============================
    public void setProductCount(int count) {
        this.productCount = count;
    }
    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    @Override
    public void addFromFile(String path) {
        try (Scanner reader = new Scanner(new File(path))) { //sử dụng scanner để đọc luồng file ở trên
            lastProdectId = Integer.parseInt(reader.nextLine());//lưu id của sản phẩm cuối cùng
            reader.nextLine();
            reader.nextLine();
            while (reader.hasNextLine()) {  //khi file cần đọc vẫn còn dòng
                productCount++;
                String s = reader.nextLine();   //đọc dòng đó
                String[] sSplit = s.split("[ ]*[|][ ]*");    //tách các thành phần cần lấy
                
                //lưu vào dữ liệu danh sách sản phẩm
                //phân tích string thành long https://docs.oracle.com/javase/8/docs/api/java/lang/Long.html
                Product product = new Product(Integer.parseInt(sSplit[0]), sSplit[1], Long.parseLong(sSplit[2]), Integer.parseInt(sSplit[3]));
                productList.add(product);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    @Override
    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter(FilePaths.PRODUCT_PATH);
            writer.write(String.valueOf(lastProdectId));
            writer.write("\n======================== DANH SACH SAN PHAM =================");
            writer.write(String.format("\n%-6s|%-25s|%-16s|%s","MA","TEN SAN PHAM", "GIA", "SO LUONG"));
            for(Product product : productList) {
                writer.write("\n" + product.getAll());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    // Thêm sản phẩm
    public void addProduct(String productName, long productPrice, int productQuantity) {
        productCount++;
        lastProdectId++;
        Product newProduct = new Product(lastProdectId, productName, productPrice, productQuantity);
        productList.add(newProduct);
        writeToFile();
    }

    // Hàm Xem danh sách sản phẩn
    public void viewProductsList() {
        System.out.printf("%-5s|%-25s|%-16s|%s\n","MÃ","TÊN SẢN PHẨM", "GIÁ", "SỐ LƯỢNG");
        for (Product product : productList) {
            System.out.printf("%-5d|%-25s|%-16s|%d\n",product.getProductId(),product.getProductName(),product.getProductPrice(),product.getProductQuantity());
        }
    }

    // Hàm Tìm sản phẩm 
    public void searchProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================MENU TÌM KIẾM=======================");
        System.out.println("1. Tìm theo tên sản phẩm");
        System.out.println("2. Tìm theo khoản giá sản phẩm");
        System.out.println("==============================================");

        int search;
        while(true) {
            System.out.print("Nhập thao tác: ");
            search = scanner.nextInt();
            scanner.nextLine();
            if((search < 1) || (search > 2)) {
                System.out.println("Thao tác không đúng.");
            } else {
                break;
            }
        }

        if(search == 1) {
            System.out.print("Nhập tên sản phẩm cần tìm: ");
            String searchProductName = scanner.nextLine();
            for (Product product : productList) {
                if(product.getProductName().toLowerCase().contains(searchProductName.toLowerCase())) {
                    System.out.println(product.getAll());
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
                    System.out.println(product.getAll());
                }
            }
        }
        
    }
    
    // Tìm sản phẩm từ Id
    public Product getProductById(int id) {
        for(Product product : productList) {
            if(product.getProductId() == id)
                return product;
        }
        return null;
    }

    // Tìm sản phẩm từ tên sản phẩm
    public Product getProductByName(String name) {
        for(Product product : productList) {
            if(product.getProductName().equals(name)) {
                return product;
            }
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
        if(product != null) {
            productList.remove(location(product));
            writeToFile();
            System.out.println("Xóa thành công.");
            product = new Product();
        } else {
            System.out.println("Sản phẩm này không tồn tại.");
        }
    }

    // Sửa sản phẩm
    public void updateProduct(int id, String newName, long newPrice, int newQuantity) {
        Product idproductToUpdate = getProductById(id); // Tìm sản phẩm theo ID
        if (idproductToUpdate != null) {
            // Cập nhật thông tin sản phẩm
            idproductToUpdate.setProductName(newName);
            idproductToUpdate.setProductPrice(newPrice);
            idproductToUpdate.setProductQuantity(newQuantity);
            
            // Ghi lại vào file
            writeToFile();
            System.out.println("Cập nhật sản phẩm thành công!");
        } else {
            System.out.println("Sản phẩm với ID " + id + " không thể được tìm thấy.");
        }
    }
}