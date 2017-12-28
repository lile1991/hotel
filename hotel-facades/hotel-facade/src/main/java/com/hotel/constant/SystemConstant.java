package com.hotel.constant;

public class SystemConstant {
    /**
     * 为null的ID字段， 均设置为0
     */
    public static final long NULL_ID = 0L;

    public static long getId(Long id) {
        return id == null ? NULL_ID : id;
    }

    public static boolean isNullId(long id) {
        return NULL_ID == id;
    }

    public static boolean isNotNullId(long id) {
        return ! isNullId(id);
    }
}
