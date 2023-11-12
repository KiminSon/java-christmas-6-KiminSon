package christmas.model;

import java.util.Map;

public class ChristmasCalculation {
    public int caculateTotal(Map<Menu.MenuItem, Integer> orderItem) {
        int totalPrice = 0;
        for (Map.Entry<Menu.MenuItem, Integer> entry : orderItem.entrySet()) {
            Menu.MenuItem menuItem = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += menuItem.getPrice() * quantity;
        }
        return totalPrice;
    }

    public String giveFreeGift(int totalPrice) {
        if (totalPrice < 120000)
            return "없음";
        return "샴페인 1개";
    }
}
