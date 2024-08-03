package br.com.blz.testjava.data;

import br.com.blz.testjava.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;

public class InventoryData {

    public static Inventory getInventory() {
        return new Inventory(
                15,
                WarehouseData.getWarehouses()
        );
    }

}
