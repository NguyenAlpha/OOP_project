package mainoop.Payment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class paymentlist {
    /**
     * Ghi hóa đơn vào file.
     * @param payment Nội dung hóa đơn từ lớp payment
     * @param filePath Đường dẫn file cần lưu hóa đơn
     */
    public void writeToFile(payment payment, String filePath) {
        // Đảm bảo payment không null và có dữ liệu
        if (payment == null || payment.getcustomer() == null) {
            System.out.println("Không có thông tin thanh toán để ghi vào file!");
            return;
        }

        // Tạo nội dung hóa đơn
        String billContent = generateBillContent(payment);

        // Thực hiện ghi file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(billContent); // Ghi nội dung hóa đơn vào file
            writer.newLine(); // Xuống dòng
            System.out.println("Hóa đơn đã được ghi vào file: " + filePath);
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    /**
     * Tạo nội dung hóa đơn từ đối tượng payment.
     * 
     * @param payment Đối tượng payment chứa thông tin hóa đơn
     * @return Chuỗi chứa nội dung hóa đơn
     */
    private String generateBillContent(payment payment) {
        // Lấy thông tin ngày giờ hiện tại
        LocalDateTime now = LocalDateTime.now();
        String dateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Xây dựng hóa đơn
        StringBuilder billBuilder = new StringBuilder();
        billBuilder.append("============== HÓA ĐƠN ================\n");
        billBuilder.append("Tên khách hàng: ").append(payment.getcustomer().getCustomerName()).append("\n");
        billBuilder.append("------------------------------------------------------\n");
        billBuilder.append(String.format("%-20s | %-10s | %-15s | %-15s\n",
                "Tên hàng", "SL", "Đơn giá", "Thành tiền"));

        // Duyệt qua các sản phẩm trong giỏ hàng
        for (var entry : payment.getcustomer().getCartItems().entrySet()) {
            String productName = entry.getKey().getProductName();
            int quantity = entry.getValue();
            long price = entry.getKey().getProductPrice();
            long total = price * quantity;

            // Thêm thông tin sản phẩm vào hóa đơn
            billBuilder.append(String.format("%-20s | %-10d | %-15d | %-15d\n",
                    productName, quantity, price, total));
        }

        // Tổng tiền
        billBuilder.append("------------------------------------------------------\n");
        billBuilder.append("Tổng cộng: ").append(payment.getTotal()).append(" VND\n");
        billBuilder.append("======================================================\n");

        return billBuilder.toString();
    }
}
