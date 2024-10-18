package main.product;

import java.util.ArrayList;

public class list_product {
    private ArrayList<product> arraylist_products = new ArrayList<product>();

    public void add_product(product p) {
        this.arraylist_products.add(p);
    }

    public void xem_list_product() {
        for(int i = 0; i <arraylist_products.size() ; i++) {
            arraylist_products.get(i).getAll();
        }
    }
}