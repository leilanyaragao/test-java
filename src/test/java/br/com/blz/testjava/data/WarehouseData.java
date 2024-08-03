package br.com.blz.testjava.data;

import br.com.blz.testjava.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class WarehouseData {

    public static List<Warehouse> getWarehouses(){
        List<Warehouse> warehouses = new ArrayList<>();

        warehouses.add(new Warehouse("SP", 12, "ECOMMERCE"));
        warehouses.add(new Warehouse("MOEMA", 3, "PHYSICAL_STORE"));

        return warehouses;
    }
}
