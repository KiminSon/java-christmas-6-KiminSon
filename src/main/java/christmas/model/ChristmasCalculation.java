package christmas.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChristmasCalculation {
    public int calculateTotal(Map<Menu.MenuItem, Integer> orderItem) {
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

    public Map<String, Integer> getDiscount(int totalPrice, int visitDay, Map<Menu.MenuItem, Integer> orderItem) {
        Map<String, Integer> discountPrize = new LinkedHashMap<>();
        if (giveFreeGift(totalPrice).equals("없음")) {
            if (totalPrice < 10000 || calculateDiscount(visitDay, orderItem).isEmpty()) {
                discountPrize.put("없음", 0);
                return discountPrize;
            }
        }
        discountPrize = calculateDiscount(visitDay, orderItem);
        if (giveFreeGift(totalPrice).equals("샴페인 1개")) {
            discountPrize.put("증정 이벤트", -25000);
        }
        return discountPrize;
    }

    public Map<String, Integer> calculateDiscount(int visitDay, Map<Menu.MenuItem, Integer> orderItem) {
        Map<String, Integer> discountPrize = new LinkedHashMap<>();
        DecemberDiscount discountDay = findDiscountDay(visitDay);
        if (discountDay.getDailyDiscount() > 0) {
            discountPrize.put("크리스마스 디데이 할인", -discountDay.getDailyDiscount());
        }
        if (!discountDay.isWeekend() && containsDessert(orderItem)) {
            discountPrize.put("평일 할인", -countDessert(orderItem));
        }
        if (discountDay.isWeekend() && containsMainCourse(orderItem)) {
            discountPrize.put("주말 할인", -countMainCourse(orderItem));
        }
        if (discountDay.isStarMarked()) {
            discountPrize.put("특별 할인", -1000);
        }
        return discountPrize;
    }

    public DecemberDiscount findDiscountDay(int visitDay) {
        DecemberDiscount discountDay = null;
        for (DecemberDiscount discount : DecemberDiscount.values()) {
            if (discount.getDate() == visitDay) {
                discountDay = discount;
                break;
            }
        }
        return discountDay;
    }

    public boolean containsDessert(Map<Menu.MenuItem, Integer> orderItem) {
        for (Menu.MenuItem item : orderItem.keySet()) {
            if (item.getMenuType() == Menu.MenuType.DESSERT) {
                return true;
            }
        }
        return false;
    }

    public int countDessert(Map<Menu.MenuItem, Integer> orderItem) {
        int totalDessert = 0;
        for (Menu.MenuItem item : orderItem.keySet()) {
            if (item.getMenuType() == Menu.MenuType.DESSERT) {
                totalDessert += orderItem.get(item);
            }
        }
        return totalDessert * 2023;
    }

    public boolean containsMainCourse(Map<Menu.MenuItem, Integer> orderItem) {
        for (Menu.MenuItem item : orderItem.keySet()) {
            if (item.getMenuType() == Menu.MenuType.MAIN_COURSE) {
                return true;
            }
        }
        return false;
    }

    public int countMainCourse(Map<Menu.MenuItem, Integer> orderItem) {
        int totalMainCourse = 0;
        for (Menu.MenuItem item : orderItem.keySet()) {
            if (item.getMenuType() == Menu.MenuType.MAIN_COURSE) {
                totalMainCourse += orderItem.get(item);
            }
        }
        return totalMainCourse * 2023;
    }

    public int getTotalDiscount(Map<String, Integer> discountPrize) {
        int totalDiscount = 0;
        for (Integer value : discountPrize.values()) {
            totalDiscount += value;
        }
        return totalDiscount;
    }

    public int getRealPrice(int totalPrice, int totalDiscount) {
        if (giveFreeGift(totalPrice).equals("없음")) {
            return totalPrice + totalDiscount;
        }
        return totalPrice + totalDiscount + 25000;
    }

    public String getBadge(int totalDiscount) {
        if (totalDiscount < -20000) {
            return "산타";
        }
        if (totalDiscount < -10000) {
            return "트리";
        }
        if (totalDiscount < -5000) {
            return "별";
        }
        return "없음";
    }
}
