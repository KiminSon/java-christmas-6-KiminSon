package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String ERROR_DATE_MASSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String ERROR_ORDER_MASSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public int readDate() {
        try {
            String input = Console.readLine();
            int visitDate = Integer.parseInt(input);
            return visitDate;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(ERROR_DATE_MASSAGE);
        }
    }

    public String readItem() {
        String input = Console.readLine();
        return input;
    }
}
