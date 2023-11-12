package christmas.model;

import java.util.Map;

public class ChristmasCalculation {
    public int caculateTotal(Map<Menu.MenuItem, Integer> orderItem){
        int totalPrice = 0;
        for (Map.Entry<Menu.MenuItem, Integer> entry : orderItem.entrySet()) {
            Menu.MenuItem menuItem = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += menuItem.getPrice() * quantity;
        }
        return totalPrice;
    }
}
