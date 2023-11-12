package christmas.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private static final String ERROR_ORDER_MASSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public Map<Menu.MenuItem, Integer> orderItem(String input) {
        Map<Menu.MenuItem, Integer> orderItems = new LinkedHashMap<>();
        String[] items = input.split(",");

        for (String item : items) {
            String[] parts = item.trim().split("-");
            int quantity = Integer.parseInt(parts[1]);

            Menu.MenuItem menuItem = findMenuItemByName(parts[0]);

            validateOrderItem(orderItems, menuItem, quantity);

            orderItems.put(menuItem, quantity);
        }

        return orderItems;
    }

    public Menu.MenuItem findMenuItemByName(String name) {
        for (Menu.MenuItem item : Menu.MenuItem.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new IllegalArgumentException(ERROR_ORDER_MASSAGE);
    }

    public void validateOrderItem(Map<Menu.MenuItem, Integer> orderItems, Menu.MenuItem menuItem, int quantity) {
        isDuplicateMenu(orderItems, menuItem);
    }

    public void isDuplicateMenu(Map<Menu.MenuItem, Integer> orderItems, Menu.MenuItem menuItem) {
        if (orderItems.containsKey(menuItem)) {
            throw new IllegalArgumentException(ERROR_ORDER_MASSAGE);
        }
    }
}