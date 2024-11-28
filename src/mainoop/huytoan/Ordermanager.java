package mainoop.huytoan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ordermanager {

    // Phương thức đọc file và trả về danh sách đơn hàng từ file
    private List<String[]> readFile(String filePath) {
        List<String[]> orders = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            List<String> tempOrder = new ArrayList<>();
            int lineCount = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue; // Bỏ qua dòng trống

                tempOrder.add(line);
                lineCount++;

                // Khi đủ 6 dòng cho một đơn hàng
                if (lineCount == 6) {
                    orders.add(tempOrder.toArray(new String[0])); // Chuyển danh sách tạm thành mảng
                    tempOrder.clear(); // Reset danh sách tạm
                    lineCount = 0; // Reset bộ đếm dòng
                }
            }

            // Kiểm tra nếu còn dữ liệu thừa (đơn hàng không đủ 6 dòng)
            if (!tempOrder.isEmpty()) {
                System.err.println("Dữ liệu không đầy đủ cho một đơn hàng, bỏ qua: " + tempOrder);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
        }
        return orders;
    }

    // Phương thức ghi danh sách đơn hàng vào file
    private void writeFile(String filePath, List<String[]> orders) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] order : orders) {
                bw.write(order[0]); // Mã đơn hàng
                bw.newLine();
                bw.write(order[1]); // Mã khách hàng
                bw.newLine();
                bw.write(order[2]); // Tên sản phẩm và số lượng
                bw.newLine();
                bw.write(order[3]); // Giá tiền
                bw.newLine();
                bw.write(order[4]); // Trạng thái đơn hàng
                bw.newLine();
                bw.write(order[5]); // Địa chỉ khách hàng
                bw.newLine(); // Thêm một dòng trống giữa các đơn hàng
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    // Phương thức hiển thị hóa đơn theo định dạng mới
    private void displayOrder(String[] order) {
        System.out.println("=====================================================");
        System.out.println("                     Hóa Đơn                        ");
        System.out.println("=====================================================");
        System.out.printf("%-20s: %s\n", "Tên khách hàng", order[1]);
        System.out.printf("%-20s: %s\n", "Địa chỉ", order[5]);
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-15s | %-5s | %-10s | %-10s\n", "Tên hàng", "SL", "Đơn giá", "Thành tiền");
        System.out.println("-----------------------------------------------------");

        // Tách thông tin sản phẩm và số lượng
        String[] productDetails = order[2].split(","); // Giả sử định dạng là "Tên sản phẩm, số lượng"
        String productName = productDetails[0].trim(); // Tên sản phẩm
        int quantity = Integer.parseInt(productDetails[1].trim()); // Số lượng
        int unitPrice = Integer.parseInt(order[3]); // Giá tiền một sản phẩm
        int totalPrice = quantity * unitPrice; // Tính thành tiền

        System.out.printf("%-15s | %-5d | %-10d | %-10d\n", productName, quantity, unitPrice, totalPrice);
        System.out.println("-----------------------------------------------------");
        System.out.printf("Tổng cộng: %d VND\n", totalPrice);
        System.out.printf("Trạng thái đơn hàng: %s\n", order[4]);
        System.out.println("=====================================================");
    }

    // Phương thức hiển thị danh sách đơn hàng
    public void manageOrdersFromFile(String filePath) {
        Scanner scanner = new Scanner(System.in);

        List<String[]> orders = readFile(filePath);
        if (orders.isEmpty()) {
            System.out.println("File không có dữ liệu hoặc không tồn tại.");
            return;
        }

        while (true) {
            System.out.println("Danh sách đơn hàng:");
            for (int i = 0; i < orders.size(); i++) {
                System.out.printf("%d | Mã đơn hàng: %s\n", i + 1, orders.get(i)[0]);
            }

            System.out.print("Nhập số thứ tự của đơn hàng để xem chi tiết (0 để thoát): ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số.");
                continue;
            }

            if (choice == 0) {
                System.out.println("Đã thoát chương trình.");
                return;
            }

            if (choice < 1 || choice > orders.size()) {
                System.out.println("Số thứ tự không hợp lệ.");
                continue;
            }

            // Hiển thị hóa đơn chi tiết
            displayOrder(orders.get(choice - 1));

            // Xác nhận trạng thái đơn hàng
            System.out.print("Xác nhận (1), hủy xác nhận (2), giữ nguyên (3): ");
            String action = scanner.nextLine().trim().toLowerCase();

            switch (action) {
                case "1": // Xác nhận đơn hàng
                    orders.get(choice - 1)[4] = "đang vận chuyển";
                    System.out.println("Đã xác nhận đơn hàng.");
                    break;
                case "2": // Hủy đơn hàng
                    orders.get(choice - 1)[4] = "đã hủy";
                    System.out.println("Đã hủy đơn hàng.");
                    break;
                case "3": // Giữ nguyên trạng thái
                    System.out.println("Giữ nguyên trạng thái đơn hàng.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, trạng thái đơn hàng không thay đổi.");
                    break;
            }

            // Ghi cập nhật vào file
            writeFile(filePath, orders);
            System.out.println("Đã cập nhật file.");
        }
    }

    // Phương thức main để chạy chương trình
    public static void main(String[] args) {
        Ordermanager orderManager = new Ordermanager();
        String filePath = "bill.txt"; // Đường dẫn file chứa danh sách đơn hàng
        orderManager.manageOrdersFromFile(filePath);
    }
}
