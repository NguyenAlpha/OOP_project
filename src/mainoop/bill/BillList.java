package mainoop.bill;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;
import mainoop.FilePaths;
import mainoop.ListInterface;
import mainoop.product.Product;
import mainoop.user.Customer;

public class BillList implements ListInterface{
    private int count;
    private ArrayList<Bill> billList = new ArrayList<>();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Bill> getBillList() {
        return billList;
    }

    public void setBillList(ArrayList<Bill> billList) {
        this.billList = billList;
    }

    @Override
    public void addFromFile(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void writeToFile() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // thêm bill
    public void addBill(Customer customer) {
        count++;
        // billList.add();
        try {
            FileWriter writer = new FileWriter(FilePaths.BILL_PATH, true);
            writer.write(count + " | " + customer.getUserId() + "\n");
            boolean check = false;
            for(Map.Entry<Product, Integer> en : customer.getCartItem().entrySet()) {
                Product product = en.getKey();
                int quantity = en.getValue();
                if(check) {
                    writer.write("    |    ");
                }
                writer.write(product.getProductName() + " , " + quantity);
                check = true;
            }
            writer.write("\n" + customer.getSumPriceProduct() + "\n");
            writer.write(customer.getCustomerAddress() + "\n");
            writer.write("dang chuan bi don hang");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
