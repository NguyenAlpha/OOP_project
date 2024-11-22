package mainoop.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List; 
import java.util.Scanner; 

public class Ordermanager {
   
   
    public void manageOrdersFromFile(String filePath) {
        Scanner scanner = new Scanner(System.in); // Tạo đối tượng Scanner để nhận đầu vào từ người dùng

        while (true) { 
            List<String> updatedOrders = new ArrayList<>(); // Danh sách lưu trữ các đơn hàng đã cập nhật

            try {
                // Kiểm tra xem file có tồn tại không
                if (!Files.exists(Paths.get(filePath))) {
                    System.out.println("File không tồn tại: " + filePath);
                    return; 
                }

                // Đọc tất cả các dòng từ file
                List<String> lines = Files.readAllLines(Paths.get(filePath));

                // Hiển thị danh sách đơn hàng
                System.out.println("Danh sách đơn hàng:");
                for (int i = 0; i < lines.size(); i++) {
                    System.out.println((i + 1) + " | " + lines.get(i)); // Hiển thị số thứ tự và đơn hàng
                }

                // Yêu cầu admin nhập số thứ tự của đơn hàng để xác nhận hoặc hủy xác nhận
                System.out.print("Nhập số thứ tự của đơn hàng để xác nhận hoặc hủy xác nhận (0 để thoát): ");
                int choice = scanner.nextInt(); // Nhận lựa chọn từ admin

                if (choice == 0) {
                    System.out.println("Đã thoát.");
                    break; // Thoát vòng lặp nếu admin chọn 0
                }

                // Kiểm tra xem số thứ tự có hợp lệ không
                if (choice < 1 || choice > lines.size()) {
                    System.out.println("Số thứ tự không hợp lệ.");
                    continue; // Quay lại đầu vòng lặp nếu số thứ tự không hợp lệ
                }

                // Lấy đơn hàng tương ứng
                String selectedOrder = lines.get(choice - 1);
                System.out.println("Đơn hàng đã chọn: " + selectedOrder);
                System.out.print("Xác nhận (y) hoặc hủy xác nhận (n)? (nhập 'h' để giữ nguyên): "); // Yêu cầu admin nhập lựa chọn

                scanner.nextLine(); // Đọc dòng còn lại
                String confirmChoice = scanner.nextLine(); // Nhận lựa chọn từ admin

                String updatedLine;
                if (confirmChoice.equalsIgnoreCase("y")) {
                    // Nếu admin chọn xác nhận, thay thế "chờ xác nhận" bằng "đã xác nhận"
                    updatedLine = selectedOrder.replace("đang chuẩn bị", "đang vận chuyển");
                    System.out.println("Đã xác nhận đơn hàng: " + updatedLine); // In ra thông báo xác nhận
                } else if (confirmChoice.equalsIgnoreCase("n")) {
                    // Nếu admin chọn hủy xác nhận, thay thế "đã xác nhận" bằng "chờ xác nhận"
                    updatedLine = selectedOrder.replace("đã xác nhận", "chờ xác nhận");
                    System.out.println("Đã hủy xác nhận đơn hàng: " + updatedLine); // In ra thông báo hủy xác nhận
                } else {
                    // Nếu không xác nhận và không hủy, giữ nguyên dòng đó
                    updatedLine = selectedOrder;
                }

                // Cập nhật danh sách đơn hàng
                for (String line : lines) {
                    if (line.equals(selectedOrder)) {
                        updatedOrders.add(updatedLine); // Thêm dòng đã cập nhật vào danh sách
                    } else {
                        updatedOrders.add(line); // Thêm các dòng không thay đổi
                    }
                }

                // Ghi lại tất cả các dòng (đã cập nhật và không thay đổi) vào file
                Files.write(Paths.get(filePath), updatedOrders);
                System.out.println("Đã cập nhật trạng thái đơn hàng vào file."); 

            } catch (IOException e) {
                e.printStackTrace(); // In ra lỗi nếu có
            }
        }

        scanner.close(); 
    }
}