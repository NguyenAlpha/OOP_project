package mainoop.huytoan;

import java.io.*;
import java.util.*;

public class Ordermanager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "Bill.txt";

        while (true) {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Hiển thị danh sách hóa đơn");
            System.out.println("2. Xác nhận hoặc thay đổi trạng thái đơn hàng");
            System.out.println("0. Thoát");
            System.out.println("==========================");

            System.out.print("Lựa chọn của bạn: ");
            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số.");
                continue;
            }

            switch (choice) {
                case 1 -> manageOrdersFromFile(filePath); // Hiển thị danh sách hóa đơn
                case 2 -> updateOrderStatus(filePath); // Xác nhận/thay đổi trạng thái đơn hàng
                case 0 -> {
                    System.out.println("Thoát chương trình.");
                    return; // Kết thúc chương trình
                }
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    // Hàm hiển thị danh sách hóa đơn
    public static void manageOrdersFromFile(String filePath) {
        List<String[]> orders = readAllOrders(filePath);
        if (orders.isEmpty()) {
            System.out.println("Không có hóa đơn nào để hiển thị.");
            return;
        }

        System.out.println("\n========== DANH SÁCH HÓA ĐƠN ==========");
        for (int i = 0; i < orders.size(); i++) {
            System.out.printf("%d. Mã đơn hàng: %s, Tên khách hàng: %s\n",
                    i + 1, orders.get(i)[0], orders.get(i)[1]);
        }
        System.out.println("=======================================");
    }

    // Hàm cập nhật trạng thái đơn hàng dựa trên lựa chọn của người dùng
    public static void updateOrderStatus(String filePath) {
        Scanner scanner = new Scanner(System.in);
        List<String[]> orders = readAllOrders(filePath);

        if (orders.isEmpty()) {
            System.out.println("Không có hóa đơn nào để cập nhật.");
            return;
        }

        System.out.println("\n========== DANH SÁCH HÓA ĐƠN ==========");
        for (int i = 0; i < orders.size(); i++) {
            System.out.printf("%d.%s, Trạng thái: %s\n",
                    i + 1, orders.get(i)[0], orders.get(i)[orders.get(i).length - 1]);
        }
        System.out.println("=======================================");

        System.out.print("Chọn số thứ tự hóa đơn để cập nhật (0 để hủy): ");
        int choice;

        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }

        if (choice == 0) {
            System.out.println("Hủy cập nhật.");
            return;
        }

        if (choice < 1 || choice > orders.size()) {
            System.out.println("Số thứ tự không hợp lệ.");
            return;
        }

        String[] selectedOrder = orders.get(choice - 1);
        System.out.println("\nChi tiết hóa đơn:");
        for (String detail : selectedOrder) {
            System.out.println(detail);
        }

        System.out.print("\nXác nhận (1), Hủy đơn hàng (2), Giữ nguyên (3): ");
        String action = scanner.nextLine().trim();

        switch (action) {
            case "1" -> selectedOrder[selectedOrder.length - 1] = "Dang van chuyen.";
            case "2" -> selectedOrder[selectedOrder.length - 1] = "Da huy.";
            case "3" -> System.out.println("Không thay đổi trạng thái.");
            default -> {
                System.out.println("Lựa chọn không hợp lệ.");
                return;
            }
        }

        // Cập nhật file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] order : orders) {
                for (String line : order) {
                    bw.write(line);
                    bw.newLine();
                }
                bw.write("======================================================");
                bw.newLine();
            }
            System.out.println("\nCập nhật trạng thái hóa đơn thành công.");
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    // Hàm đọc toàn bộ hóa đơn từ file
    private static List<String[]> readAllOrders(String filePath) {
        List<String[]> orders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            List<String> currentOrder = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("=")) { // Dấu phân cách giữa các hóa đơn
                    if (!currentOrder.isEmpty()) {
                        orders.add(currentOrder.toArray(new String[0]));
                        currentOrder.clear();
                    }
                } else {
                    currentOrder.add(line);
                }
            }
            if (!currentOrder.isEmpty()) { // Thêm hóa đơn cuối cùng
                orders.add(currentOrder.toArray(new String[0]));
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
        }
        return orders;
    }
}
