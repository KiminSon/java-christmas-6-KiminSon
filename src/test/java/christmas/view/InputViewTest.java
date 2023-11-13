package christmas.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class InputViewTest {
    private static final String ERROR_DATE_MASSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String ERROR_ORDER_MASSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    @DisplayName("제대로 된 날짜를 입력하면 정상 동작")
    @Test
    void testValidDate() {
        InputView inputView = new InputView();
        inputView.validateReadDate("15");
    }

    @DisplayName("1보다 작은 값을 넣으면 예외 처리")
    @Test
    void testDateBelowRange() {
        InputView inputView = new InputView();
        assertThatThrownBy(() -> inputView.validateReadDate("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_DATE_MASSAGE);
    }

    @DisplayName("31보다 큰 값을 넣으면 예외 처리")
    @Test
    void testDateAboveRange() {
        InputView inputView = new InputView();
        assertThatThrownBy(() -> inputView.validateReadDate("32"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_DATE_MASSAGE);
    }

    @DisplayName("숫자로 바꿀 수 없는 값을 넣으면 예외 처리")
    @Test
    void testInvalidDate() {
        InputView inputView = new InputView();
        assertThatThrownBy(() -> inputView.validateReadDate("1 "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_DATE_MASSAGE);
    }

    @DisplayName("정규식을 만족하면 정상적으로 작동")
    @Test
    void testValidItemInput() {
        InputView inputView = new InputView();
        inputView.validateReadItem("아-1");
        inputView.validateReadItem("아-1,바-2");
    }

    @DisplayName("정규식을 만족하지 않은 입력은 예외 처리")
    @Test
    void testInvalidItemInput() {
        InputView inputView = new InputView();
        assertThatThrownBy(() -> inputView.validateReadItem("아"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_ORDER_MASSAGE);
        assertThatThrownBy(() -> inputView.validateReadItem("1-아"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_ORDER_MASSAGE);
        assertThatThrownBy(() -> inputView.validateReadItem("아-1,바"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_ORDER_MASSAGE);
    }
}
