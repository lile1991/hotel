package com.hotel.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CheckStateEnum {
    RESERVE("已预定"), CANCELED_RESERVE("已取消预定"), CHECK_IN("已入住"), LEFT("已退房");

    public final String desc;
}
