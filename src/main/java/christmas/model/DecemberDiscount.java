package christmas.model;

public enum DecemberDiscount {
    DAY_1(1, 1000, true, false),
    DAY_2(2, 1100, true, false),
    DAY_3(3, 1200, false, true),
    DAY_4(4, 1300, false, false),
    DAY_5(5, 1400, false, false),
    DAY_6(6, 1500, false, false),
    DAY_7(7, 1600, false, false),
    DAY_8(8, 1700, true, false),
    DAY_9(9, 1800, true, false),
    DAY_10(10, 1900, false, true),
    DAY_11(11, 2000, false, false),
    DAY_12(12, 2100, false, false),
    DAY_13(13, 2200, false, false),
    DAY_14(14, 2300, false, false),
    DAY_15(15, 2400, true, false),
    DAY_16(16, 2500, true, false),
    DAY_17(17, 2600, false, true),
    DAY_18(18, 2700, false, false),
    DAY_19(19, 2800, false, false),
    DAY_20(20, 2900, false, false),
    DAY_21(21, 3000, false, false),
    DAY_22(22, 3100, true, false),
    DAY_23(23, 3200, true, false),
    DAY_24(24, 3300, false, true),
    DAY_25(25, 3400, false, true),
    DAY_26(26, 0, false, false),
    DAY_27(27, 0, false, false),
    DAY_28(28, 0, false, false),
    DAY_29(29, 0, true, false),
    DAY_30(30, 0, true, false),
    DAY_31(31, 0, false, true);

    private final int date;
    private final int dailyDiscount;
    private final boolean isWeekend;
    private final boolean isStarMarked;

    DecemberDiscount(int date, int dailyDiscount, boolean isWeekend, boolean isStarMarked) {
        this.date = date;
        this.dailyDiscount = dailyDiscount;
        this.isWeekend = isWeekend;
        this.isStarMarked = isStarMarked;
    }

    public int getDate() {
        return date;
    }

    public int getDailyDiscount() {
        return dailyDiscount;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public boolean isStarMarked() {
        return isStarMarked;
    }
}
