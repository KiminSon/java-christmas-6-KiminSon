package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderTest {
    private static final String ERROR_ORDER_MASSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    @DisplayName("제대로 된 메뉴를 입력하면 정상 동작")
    @Test
    void testOrderItemWithValidInput() {
        Order order = new Order();
        Map<Menu.MenuItem, Integer> result = order.orderItem("양송이수프-1,타파스-2");

        assertThat(result).isNotNull();
        assertThat(result.keySet()).contains(Menu.MenuItem.MUSHROOM_SOUP, Menu.MenuItem.TAPAS);
        assertThat(result.get(Menu.MenuItem.MUSHROOM_SOUP)).isEqualTo(1);
        assertThat(result.get(Menu.MenuItem.TAPAS)).isEqualTo(2);
    }

    @DisplayName("올바른 메뉴 이름으로 MenuItem을 정상 동작")
    @Test
    void testFindMenuItemByNameWithValidName() {
        Order order = new Order();
        Menu.MenuItem menuItem = order.findMenuItemByName("양송이수프");

        assertThat(menuItem).isNotNull();
        assertThat(menuItem).isEqualTo(Menu.MenuItem.MUSHROOM_SOUP);
    }

    @DisplayName("존재하지 않는 메뉴 이름이면 예외 처리")
    @Test
    void testFindMenuItemByNameWithInvalidName() {
        Order order = new Order();
        assertThatThrownBy(() -> order.findMenuItemByName("없는메뉴"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_ORDER_MASSAGE);
    }

    @DisplayName("수량이 1보다 작은 경우 예외 처리")
    @Test
    void testIsValidRangeWithQuantityLessThanOne() {
        Order order = new Order();
        assertThatThrownBy(() -> order.isValidRange(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_ORDER_MASSAGE);
    }

    @DisplayName("수량이 20보다 큰 경우 예외 처리")
    @Test
    void testIsValidRangeWithQuantityGreaterThanTwenty() {
        Order order = new Order();
        assertThatThrownBy(() -> order.isValidRange(21))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_ORDER_MASSAGE);
    }

    @DisplayName("수량이 1에서 20 사이인 경우 정상 작동")
    @Test
    void testIsValidRangeWithValidQuantity() {
        Order order = new Order();
        order.isValidRange(10);
    }

    @DisplayName("음료가 아닌 다른 메뉴 유형이 포함된 경우 정상 작동")
    @Test
    void testIsAllBeveragesWithNonBeverageItem() {
        Order order = new Order();
        Map<Menu.MenuItem, Integer> orderItems = Map.of(
                Menu.MenuItem.MUSHROOM_SOUP, 1,
                Menu.MenuItem.RED_WINE, 1
        );

        order.isAllBeverages(orderItems);
    }

    @DisplayName("오직 음료만 포함된 경우 예외 처리")
    @Test
    void testIsAllBeveragesWithOnlyBeverages() {
        Order order = new Order();
        Map<Menu.MenuItem, Integer> orderItems = Map.of(
                Menu.MenuItem.RED_WINE, 1,
                Menu.MenuItem.CHAMPAGNE, 1
        );

        assertThatThrownBy(() -> order.isAllBeverages(orderItems))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_ORDER_MASSAGE);
    }

    @DisplayName("다른 메뉴 항목만 있을 때 정상 동작")
    @Test
    void testIsDuplicateMenuWithDifferentItems() {
        Order order = new Order();
        Map<Menu.MenuItem, Integer> orderItems = new HashMap<>();
        orderItems.put(Menu.MenuItem.MUSHROOM_SOUP, 1);

        order.isDuplicateMenu(orderItems, Menu.MenuItem.TAPAS);
    }

    @DisplayName("동일한 메뉴 항목이 중복될 때 예외 처리")
    @Test
    void testIsDuplicateMenuWithDuplicateItems() {
        Order order = new Order();
        Map<Menu.MenuItem, Integer> orderItems = new HashMap<>();
        orderItems.put(Menu.MenuItem.MUSHROOM_SOUP, 1);

        assertThatThrownBy(() -> order.isDuplicateMenu(orderItems, Menu.MenuItem.MUSHROOM_SOUP))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_ORDER_MASSAGE);
    }
}
