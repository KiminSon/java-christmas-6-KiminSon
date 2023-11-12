package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {


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
