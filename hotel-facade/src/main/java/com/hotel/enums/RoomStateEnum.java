package com.hotel.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoomStateEnum {
    EMPTY("空置"), RESERVE("预定"), CHECK_IN("入住"), STOP("停用");

    public final String desc;
}
