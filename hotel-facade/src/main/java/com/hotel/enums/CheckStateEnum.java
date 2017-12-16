package com.hotel.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CheckStateEnum {
    RESERVE("预定"), CANCELED_RESERVE("取消预定"), CHECK_IN("入住"), LEFT("退房");

    public final String desc;
}
