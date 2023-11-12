package christmas.model;

import java.util.HashMap;
import java.util.Map;

public class Order {
    public Map<Menu.MenuItem, Integer> orderItem(String input) throws IllegalArgumentException {
        return parseOrder(input);
    }

    public Map<Menu.MenuItem, Integer> parseOrder(String input) throws IllegalArgumentException {
        Map<Menu.MenuItem, Integer> orderItems = new HashMap<>();
        String[] items = input.split(",");

        for (String item : items) {
            String[] parts = item.trim().split("-");
            String name = parts[0];
            int quantity = Integer.parseInt(parts[1]);

            Menu.MenuItem menuItem = findMenuItemByName(name);

            if (orderItems.containsKey(menuItem)) {
                throw new IllegalArgumentException("중복된 메뉴 항목: " + name);
            }

            orderItems.put(menuItem, quantity);
        }

        return orderItems;
    }

    public static Menu.MenuItem findMenuItemByName(String name) {
        for (Menu.MenuItem item : Menu.MenuItem.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new IllegalArgumentException("없는 메뉴 항목: " + name);
    }
}
