package mainoop.user;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ordermanager {

    // Đọc file và trả về danh sách đơn hàng từ ShoppingCart.txt
    private List<String[]> readFile(String filePath) {
        List<String[]> orders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<String> tempOrder = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Bỏ qua dòng trống
                tempOrder.add(line.trim());

                // Khi đủ 4 dòng cho 1 đơn hàng (mã khách, sản phẩm, tổng tiền, trạng thái)
                if (tempOrder.size() == 4) {
                    orders.add(tempOrder.toArray(String[]::new));
                    tempOrder.clear();
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
        }
        return orders;
    }

    // Ghi danh sách đơn hàng vào file ShoppingCart.txt (cập nhật trạng thái)
    private void writeFile(String filePath, List<String[]> orders) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] order : orders) {
                for (String line : order) {
                    bw.write(line);
                    bw.newLine();
                }
                bw.newLine(); // Thêm dòng trống giữa các đơn hàng
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    // Quản lý trạng thái đơn hàng từ file
    public void manageOrdersFromFile(String filePath) {
        Scanner scanner = new Scanner(System.in);

        // Đọc danh sách đơn hàng từ file
        List<String[]> orders = readFile(filePath);
        if (orders.isEmpty()) {
            System.out.println("File không có dữ liệu hoặc không tồn tại.");
            return;
        }

        // Hiển thị danh sách đơn hàng
        while (true) {
            System.out.println("Danh sách đơn hàng:");
            for (int i = 0; i < orders.size(); i++) {
                String[] order = orders.get(i);
                System.out.printf("%d | Mã khách hàng: %s\n", i + 1, order[0]);
                System.out.printf("  Sản phẩm: %s\n", order[1]);
                System.out.printf("  Tổng tiền: %s\n", order[2]);
                System.out.printf("  Trạng thái: %s\n", order[3]);
            }

            // Yêu cầu admin nhập lựa chọn
            System.out.print("Nhập số thứ tự của đơn hàng để cập nhật (0 để thoát): ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số.");
                continue;
            }

            if (choice == 0) {
                System.out.println("Đã thoát chương trình.");
                break;
            }

            if (choice < 1 || choice > orders.size()) {
                System.out.println("Số thứ tự không hợp lệ.");
                continue;
            }

            // Lấy đơn hàng được chọn
            String[] selectedOrder = orders.get(choice - 1);
            System.out.printf("Đơn hàng đã chọn:\n  Mã khách hàng: %s\n  Sản phẩm: %s\n  Tổng tiền: %s\n  Trạng thái: %s\n",
                    selectedOrder[0], selectedOrder[1], selectedOrder[2], selectedOrder[3]);

            System.out.print("Xác nhận (y), hủy xác nhận (n), giữ nguyên (h): ");
            String action = scanner.nextLine().trim().toLowerCase();

            // Xử lý cập nhật trạng thái đơn hàng
            switch (action) {
                case "1":
                    selectedOrder[3] = "đang vận chuyển";
                    System.out.println("Đã xác nhận đơn hàng.");
                    break;
                case "2":
                    selectedOrder[3] = "đã hủy";
                    System.out.println("Đã hủy đơn hàng.");
                    break;
                case "3":
                    System.out.println("Giữ nguyên trạng thái đơn hàng.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, trạng thái đơn hàng không thay đổi.");
                    break;
            }

            // Ghi lại danh sách đơn hàng vào file
            writeFile(filePath, orders);
            System.out.println("Đã cập nhật file.");
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Ordermanager orderManager = new Ordermanager(); // Đảm bảo tên lớp đúng
        String filePath = "src/mainoop/data/Bill.txt"; // Đường dẫn đến file
        orderManager.manageOrdersFromFile(filePath);
    }
}
