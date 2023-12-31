package christmas.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private static final String ERROR_ORDER_MASSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public Map<Menu.MenuItem, Integer> orderItem(String input) {
        Map<Menu.MenuItem, Integer> orderItems = new LinkedHashMap<>();
        String[] items = input.split(",");
        int totalQuantity = 0;
        for (String item : items) {
            String[] parts = item.trim().split("-");
            int quantity = Integer.parseInt(parts[1]);
            totalQuantity += quantity;
            Menu.MenuItem menuItem = findMenuItemByName(parts[0]);
            validateQuantity(totalQuantity, quantity);
            isDuplicateMenu(orderItems, menuItem);

            orderItems.put(menuItem, quantity);
        }
        isAllBeverages(orderItems);
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

    public void validateQuantity(int totalQuantity, int quantity) {
        isValidRange(totalQuantity);
        isValidRange(quantity);
    }

    public void isValidRange(int quantity) {
        if (quantity < 1 || quantity > 20) {
            throw new IllegalArgumentException(ERROR_ORDER_MASSAGE);
        }
    }

    public void isAllBeverages(Map<Menu.MenuItem, Integer> orderItems) {
        for (Menu.MenuItem item : orderItems.keySet()) {
            if (item.getMenuType() != Menu.MenuType.BEVERAGE) {
                return;
            }
        }
        throw new IllegalArgumentException(ERROR_ORDER_MASSAGE);
    }

    public void isDuplicateMenu(Map<Menu.MenuItem, Integer> orderItems, Menu.MenuItem menuItem) {
        if (orderItems.containsKey(menuItem)) {
            throw new IllegalArgumentException(ERROR_ORDER_MASSAGE);
        }
    }
}
