package com.hotel.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CheckStateEnum {
    RESERVE("预定"), CHECK_IN("入住"), LEAVE("离店"), CANCEL("取消");

    public final String desc;
}
