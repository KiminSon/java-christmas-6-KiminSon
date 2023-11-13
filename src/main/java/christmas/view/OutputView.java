package christmas.view;

import christmas.model.Menu;

import java.util.Map;

public class OutputView {
    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printWelcome() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printSelectVisitDay() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void printInputItem() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public void printMenu(int visitDay, Map<Menu.MenuItem, Integer> orderItem) {
        System.out.println("12월 " + visitDay + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

        System.out.println("\n<주문 메뉴>");
        for (Map.Entry<Menu.MenuItem, Integer> entry : orderItem.entrySet()) {
            System.out.println(entry.getKey().getName() + " " + entry.getValue() + "개");
        }
    }

    public void printTotalPrice(int totalPrice) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(String.format("%,d원", totalPrice));
    }

    public void printFreeGift(String freeGift) {
        System.out.println("\n<증정 메뉴>");
        System.out.println(freeGift);
    }

    public void printDiscount(Map<String, Integer> discountPrize) {
        System.out.println("\n<혜택 내역>");

        String firstKey = discountPrize.keySet().iterator().next();
        if (firstKey.equals("없음")) {
            System.out.println(firstKey);
            return;
        }
        for (Map.Entry<String, Integer> entry : discountPrize.entrySet()) {
            String formattedValue = String.format("%,d원", entry.getValue());
            System.out.println(entry.getKey() + ": " + formattedValue);
        }
    }

    public void printTotalDiscount(int totalDiscount) {
        System.out.println("\n<총혜택 금액>");
        System.out.println(String.format("%,d원", totalDiscount));
    }

    public void printRealPrice(int realPrice) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d원", realPrice));
    }
}
