package main.input;

import java.io.File;  // thêm class File
import java.io.FileNotFoundException;   //sử lý ngoại lệ file

import java.util.Scanner;

import main.product.list_product;
import main.product.product;

//class này dùng để đọc danh sách sản phẩm được nhập từ file product.txt
public class read_list_product {
    //tạo object danh sách sản phẩm
    private list_product list_product = new list_product();

    //hàm đọc file và trả về danh sách sản phẩm sau khi đọc
    public list_product read_list_product_method() {
        try {
            File read = new File("src/main/input/product.txt"); //kết nối file muốn đọc vào class file
            Scanner reader = new Scanner(read); //sử dụng scanner để đọc luồng file ở trên
            
            while (reader.hasNextLine()) {  //khi file cần đọc vẫn còn dòng
                String s = reader.nextLine();   //đọc dòng đó
                String[] s_split = s.split(" [|] ");    //tách các thành phần cần lấy

                //lưu vào dữ liệu danh sách sản phẩm
                //phân tích string thành long https://docs.oracle.com/javase/8/docs/api/java/lang/Long.html
                product pd = new product(s_split[0], s_split[1], Long.parseLong(s_split[2]));
                list_product.add_product(pd);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return list_product;
    }
}