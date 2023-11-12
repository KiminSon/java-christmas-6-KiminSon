package christmas.controller;

import christmas.model.Menu;
import christmas.model.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class ChristmasController {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final Order order = new Order();

    public void startChristmas() {
        int visitDay = inputDate();
        Map<Menu.MenuItem, Integer> orderItem = inputItem();
        outputView.printMenu(visitDay, orderItem);
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
}
