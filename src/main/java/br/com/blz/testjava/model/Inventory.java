package br.com.blz.testjava.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    private int quantity;
    private List<Warehouse> warehouses;

    void addWarehouse(Warehouse warehouse) {
        this.warehouses.add(warehouse);
        quantity();

    }

    public void quantity(){
        this.quantity =  getQuantity();
    }

    public int getQuantity() {
        int quantity = 0;
        for (Warehouse warehouse : warehouses) {
            quantity += warehouse.getQuantity();
        }
        return quantity;
    }
}
