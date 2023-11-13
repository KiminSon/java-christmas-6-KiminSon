package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ChristmasCalculationTest {
    @DisplayName("총합 계산이 정확한지 확인")
    @Test
    void testCalculateTotal() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<Menu.MenuItem, Integer> orderItems = Map.of(
                Menu.MenuItem.MUSHROOM_SOUP, 2,
                Menu.MenuItem.TAPAS, 3
        );

        int totalPrice = calculation.calculateTotal(orderItems);

        assertThat(totalPrice).isEqualTo(28500);
    }

    @DisplayName("총 금액이 120,000원 이상일 때 샴페인 1개 반환 확인")
    @Test
    void testGiveFreeGiftWithTotalPriceAboveThreshold() {
        ChristmasCalculation calculation = new ChristmasCalculation();

        String gift = calculation.giveFreeGift(120000);

        assertThat(gift).isEqualTo("샴페인 1개");
    }

    @DisplayName("총 금액이 120,000원 미만일 때 없음 반환 확인")
    @Test
    void testGiveFreeGiftWithTotalPriceBelowThreshold() {
        ChristmasCalculation calculation = new ChristmasCalculation();

        String gift = calculation.giveFreeGift(119999);

        assertThat(gift).isEqualTo("없음");
    }

    @DisplayName("giveFreeGift가 없음이고 totalPrice가 10,000원 미만일 때 없음 반환 확인")
    @Test
    void testGetDiscountWithNoGiftAndTotalPriceBelowThreshold() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<String, Integer> discount = calculation.getDiscount(9000, 5, new LinkedHashMap<>());

        assertThat(discount).containsExactly(Map.entry("없음", 0));
    }

    @DisplayName("giveFreeGift가 없음이고 calculateDiscount가 비어 있을 때 없음 반환 확인")
    @Test
    void testGetDiscountWithNoGiftAndEmptyCalculateDiscount() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<String, Integer> discount = calculation.getDiscount(110000, 26, new LinkedHashMap<>());

        assertThat(discount).containsExactly(Map.entry("없음", 0));
    }

    @DisplayName("giveFreeGift가 없음이 아닐 때 증정 이벤트와 -25000 반환 확인")
    @Test
    void testGetDiscountWithGift() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<Menu.MenuItem, Integer> orderItems = new LinkedHashMap<>();
        orderItems.put(Menu.MenuItem.MUSHROOM_SOUP, 10);

        Map<String, Integer> discount = calculation.getDiscount(130000, 5, orderItems);

        assertThat(discount).containsKey("증정 이벤트");
        assertThat(discount.get("증정 이벤트")).isEqualTo(-25000);
    }

    @DisplayName("크리스마스 디데이 할인 적용 확인")
    @Test
    void testCalculateDiscountWithChristmasDayDiscount() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<Menu.MenuItem, Integer> orderItems = new LinkedHashMap<>();

        Map<String, Integer> discount = calculation.calculateDiscount(1, orderItems);

        assertThat(discount).containsKey("크리스마스 디데이 할인");
    }

    @DisplayName("평일 할인 적용 확인")
    @Test
    void testCalculateDiscountWithWeekdayDiscount() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<Menu.MenuItem, Integer> orderItems = new LinkedHashMap<>();
        orderItems.put(Menu.MenuItem.CHOCOLATE_CAKE, 10);

        Map<String, Integer> discount = calculation.calculateDiscount(6, orderItems);

        assertThat(discount).containsKey("평일 할인");
    }

    @DisplayName("주말 할인 적용 확인")
    @Test
    void testCalculateDiscountWithWeekendDiscount() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<Menu.MenuItem, Integer> orderItems = new LinkedHashMap<>();
        orderItems.put(Menu.MenuItem.BBQ_RIBS, 3);

        Map<String, Integer> discount = calculation.calculateDiscount(1, orderItems);
        assertThat(discount).containsKey("주말 할인");
    }

    @DisplayName("특별 할인 적용 확인")
    @Test
    void testCalculateDiscountWithSpecialDiscount() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<Menu.MenuItem, Integer> orderItems = new LinkedHashMap<>();

        Map<String, Integer> discount = calculation.calculateDiscount(25, orderItems);

        assertThat(discount).containsKey("특별 할인");
    }

    @DisplayName("특정 날짜에 맞는 DecemberDiscount 반환 확인")
    @Test
    void testFindDiscountDay() {
        ChristmasCalculation calculation = new ChristmasCalculation();

        DecemberDiscount discount = calculation.findDiscountDay(5);

        assertThat(discount).isNotNull();
        assertThat(discount).isEqualTo(DecemberDiscount.DAY_5);
    }

    @DisplayName("디저트가 있을 때 true 반환 확인")
    @Test
    void testContainsDessertWithDessert() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<Menu.MenuItem, Integer> orderItems = Map.of(Menu.MenuItem.CHOCOLATE_CAKE, 1);

        boolean result = calculation.containsDessert(orderItems);

        assertThat(result).isTrue();
    }

    @DisplayName("디저트가 없을 때 false 반환 확인")
    @Test
    void testContainsDessertWithoutDessert() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<Menu.MenuItem, Integer> orderItems = Map.of(Menu.MenuItem.BBQ_RIBS, 1);

        boolean result = calculation.containsDessert(orderItems);

        assertThat(result).isFalse();
    }

    @DisplayName("디저트 수량에 따른 값 반환 확인")
    @Test
    void testCountDessert() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<Menu.MenuItem, Integer> orderItems = Map.of(Menu.MenuItem.CHOCOLATE_CAKE, 2);

        int result = calculation.countDessert(orderItems);

        assertThat(result).isEqualTo(2 * 2023);
    }

    @DisplayName("메인 코스가 있을 때 true 반환 확인")
    @Test
    void testContainsMainCourseWithMainCourse() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<Menu.MenuItem, Integer> orderItems = Map.of(Menu.MenuItem.BBQ_RIBS, 1);

        boolean result = calculation.containsMainCourse(orderItems);

        assertThat(result).isTrue();
    }

    @DisplayName("메인 코스가 없을 때 false 반환 확인")
    @Test
    void testContainsMainCourseWithoutMainCourse() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<Menu.MenuItem, Integer> orderItems = Map.of(Menu.MenuItem.CHOCOLATE_CAKE, 1);

        boolean result = calculation.containsMainCourse(orderItems);

        assertThat(result).isFalse();
    }

    @DisplayName("메인 코스 수량에 따른 값 반환 확인")
    @Test
    void testCountMainCourse() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<Menu.MenuItem, Integer> orderItems = Map.of(Menu.MenuItem.BBQ_RIBS, 3);

        int result = calculation.countMainCourse(orderItems);

        assertThat(result).isEqualTo(3 * 2023);
    }

    @DisplayName("totalDiscount를 올바르게 반환 확인")
    @Test
    void testGetTotalDiscount() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        Map<String, Integer> discountPrize = Map.of(
                "크리스마스 디데이 할인", -1000,
                "특별 할인", -1000
        );

        int totalDiscount = calculation.getTotalDiscount(discountPrize);

        assertThat(totalDiscount).isEqualTo(-2000);
    }

    @DisplayName("giveFreeGift가 없음일 때 올바른 가격 반환 확인")
    @Test
    void testGetRealPriceWithNoGift() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        int totalPrice = 100000;
        int totalDiscount = -2000;

        int realPrice = calculation.getRealPrice(totalPrice, totalDiscount);

        assertThat(realPrice).isEqualTo(100000 - 2000);
    }

    @DisplayName("giveFreeGift가 존재할 때 올바른 가격 반환 확인")
    @Test
    void testGetRealPriceWithGift() {
        ChristmasCalculation calculation = new ChristmasCalculation();
        int totalPrice = 130000;
        int totalDiscount = -3000;

        int realPrice = calculation.getRealPrice(totalPrice, totalDiscount);

        assertThat(realPrice).isEqualTo(130000 - 3000 + 25000);
    }

    @DisplayName("총 할인 금액이 -20000보다 작을 때 산타 반환 확인")
    @Test
    void testGetBadgeWithSanta() {
        ChristmasCalculation calculation = new ChristmasCalculation();

        String badge = calculation.getBadge(-21000);

        assertThat(badge).isEqualTo("산타");
    }

    @DisplayName("총 할인 금액이 -10000보다 작고 -20000 이상일 때 트리 반환 확인")
    @Test
    void testGetBadgeWithTree() {
        ChristmasCalculation calculation = new ChristmasCalculation();

        String badge = calculation.getBadge(-15000);

        assertThat(badge).isEqualTo("트리");
    }

    @DisplayName("총 할인 금액이 -5000보다 작고 -10000 이상일 때 별 반환 확인")
    @Test
    void testGetBadgeWithStar() {
        ChristmasCalculation calculation = new ChristmasCalculation();

        String badge = calculation.getBadge(-8000);

        assertThat(badge).isEqualTo("별");
    }

    @DisplayName("총 할인 금액이 -5000 이상일 때 없음 반환 확인")
    @Test
    void testGetBadgeWithNone() {
        ChristmasCalculation calculation = new ChristmasCalculation();

        String badge = calculation.getBadge(-4000);

        assertThat(badge).isEqualTo("없음");
    }
}
