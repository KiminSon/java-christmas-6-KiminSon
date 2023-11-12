package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public void startChristmas() {
        int visitDay = inputDate();
    }

    private int inputDate() {
        outputView.printWelcome();
        while (true) {
            try {
                return inputView.readDate();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
