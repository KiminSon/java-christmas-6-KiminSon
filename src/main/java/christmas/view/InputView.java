package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Validation;

public class InputView {
    private static final Validation validation = new Validation();

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        validation.validateReadDate(input);
        return Integer.parseInt(input);
    }
}
