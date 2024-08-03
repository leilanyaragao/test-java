package br.com.blz.testjava.data;

import br.com.blz.testjava.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductData {

    public static Product getProject(){
        return new Product(
                43264,
                "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
                InventoryData.getInventory()
        );
    }

}
