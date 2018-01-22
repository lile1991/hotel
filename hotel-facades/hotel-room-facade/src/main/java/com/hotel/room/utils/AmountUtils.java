package com.hotel.room.utils;

import java.math.BigDecimal;

public class AmountUtils {

    public static final BigDecimal RATE = BigDecimal.valueOf(100);

    public static Long toLong(BigDecimal value) {
        return value.multiply(RATE).longValue();
    }
}
