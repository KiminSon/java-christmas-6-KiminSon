package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String ERROR_ORDER_MASSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public int readDate() {
        String input = Console.readLine();
        validateReadDate(input);
        return Integer.parseInt(input);
    }

    public String readItem() {
        String input = Console.readLine();
        validateReadItem(input);
        return input;
    }

    public void validateReadDate(String input) {

    }

    public void validateReadItem(String input) {

    }
}
