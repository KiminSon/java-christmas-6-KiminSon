package christmas.controller;

import christmas.model.ChristmasCalculation;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class ChristmasController {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final Order order = new Order();
    private static final ChristmasCalculation christmasCalculation = new ChristmasCalculation();

    public void startChristmas() {
        int visitDay = inputDate();
        Map<Menu.MenuItem, Integer> orderItem = inputItem();
        outputView.printMenu(visitDay, orderItem);
        int totalPrice = calculateTotalPrice(orderItem);
        outputView.printFreeGift(christmasCalculation.giveFreeGift(totalPrice));
        Map<String, Integer> discountPrize = calculateDiscount(totalPrice, visitDay, orderItem);
        int totalDiscount = calculateTotalDiscount(discountPrize);
    }

    public int inputDate() {
        outputView.printWelcome();
        while (true) {
            try {
                outputView.printSelectVisitDay();
                return inputView.readDate();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public Map<Menu.MenuItem, Integer> inputItem() {
        while (true) {
            try {
                outputView.printInputItem();
                return order.orderItem(inputView.readItem());
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public int calculateTotalPrice(Map<Menu.MenuItem, Integer> orderItem) {
        int totalPrice = christmasCalculation.caculateTotal(orderItem);
        outputView.printTotalPrice(totalPrice);
        return totalPrice;
    }

    public Map<String, Integer> calculateDiscount(int totalPrice, int visitDay, Map<Menu.MenuItem, Integer> orderItem) {
        Map<String, Integer> discountPrize = christmasCalculation.getDiscount(totalPrice, visitDay, orderItem);
        outputView.printDiscount(discountPrize);
        return discountPrize;
    }

    public int calculateTotalDiscount(Map<String, Integer> discountPrize) {
        int totalDiscount = christmasCalculation.getTotalDiscount(discountPrize);
        outputView.printTotalDiscount(totalDiscount);
        return totalDiscount;
    }
}
