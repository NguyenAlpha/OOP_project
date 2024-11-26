package mainoop.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PayedBill {

    public static void viewOrderHistory(String filePath) throws IOException {
        File file = new File(filePath);

        // Kiểm tra file có tồn tại không
        if (!file.exists()) {
            System.out.println("LỖI: File không tồn tại! Đường dẫn: " + filePath);
            return;
        }

        // Kiểm tra nếu file rỗng
        if (file.length() == 0) {
            System.out.println("LỖI: File rỗng! Không có dữ liệu để đọc.");
            return;
        }

        // Đọc file và hiển thị nội dung theo định dạng
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== XEM LỊCH SỬ ĐƠN HÀNG ===");
        try {
            viewOrderHistory("src/mainoop/data/Payedbill.txt"); // Thay đổi đường dẫn tới file của bạn
        } catch (IOException e) {
            System.out.println("LỖI: Không thể đọc file. " + e.getMessage());
        }
    }
}