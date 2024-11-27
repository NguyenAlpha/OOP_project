package mainoop.Payment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import mainoop.product.Product;

public class paymentlist {
    /**
     * Ghi hóa đơn vào file.
     *
     * @param payment         Đối tượng payment chứa thông tin thanh toán
     * @param filePath        Đường dẫn file cần lưu hóa đơn
     * @param isOrderConfirmed Trạng thái đơn hàng: true nếu đã giao, false nếu đang giao
     */
    public void writeToFile(payment payment, String filePath, boolean isOrderConfirmed) {
        // Kiểm tra tính hợp lệ của đối tượng payment
        if (payment == null || payment.getcustomer() == null) {
            System.out.println("Không có thông tin thanh toán để ghi vào file!");
            return;
        }

        // Lấy nội dung hóa đơn
        String billContent = generateBillContent(payment, isOrderConfirmed);

        // Thực hiện ghi file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(billContent); // Ghi nội dung hóa đơn vào file
            writer.newLine(); // Xuống dòng
            System.out.println("Hóa đơn đã được thanh toán thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    /**
     * Tạo nội dung hóa đơn từ đối tượng payment.
     *
     * @param payment          Đối tượng payment chứa thông tin hóa đơn
     * @param isOrderConfirmed Trạng thái đơn hàng
     * @return Chuỗi chứa nội dung hóa đơn
     */
    private String generateBillContent(payment payment, boolean isOrderConfirmed) {
        // Xây dựng hóa đơn
        StringBuilder billBuilder = new StringBuilder();
        billBuilder.append("========================= Hoa Don =====================\n");
        billBuilder.append("Ten khach hang: ").append(payment.getcustomer().getCustomerName()).append("\n");
        billBuilder.append("Dia chi: ").append(payment.getcustomer().getCustomerAddress()).append("\n");
        billBuilder.append("------------------------------------------------------\n");
        billBuilder.append(String.format("%-20s | %-10s | %-15s | %-15s\n",
                "Ten hang", "SL", "Don gia", "Thanh tien"));

        // Duyệt qua các sản phẩm trong giỏ hàng
        long totalAmount = 0;
        for (Map.Entry<Product, Integer> entry : payment.getcustomer().getCartItem().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue(); 
            long price = product.getProductPrice();
            long total = price * quantity;
            totalAmount += total;

            // Thêm thông tin sản phẩm vào hóa đơn
            billBuilder.append(String.format("%-20s | %-10d | %-15d | %-15d\n",
                    product.getProductName(), quantity, price, total));
        }

        // Tổng tiền
        billBuilder.append("------------------------------------------------------\n");
        billBuilder.append("Tong cong: ").append(totalAmount).append(" VND\n");

        // Trạng thái đơn hàng
        if (isOrderConfirmed) {
            billBuilder.append("Don hang dang giao.\n");
        } else {
            billBuilder.append("Dang chuan bi don hang.\n");
        }

        billBuilder.append("======================================================\n");
        return billBuilder.toString();
    }
    
}
