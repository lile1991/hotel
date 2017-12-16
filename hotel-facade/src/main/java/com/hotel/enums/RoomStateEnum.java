package com.hotel.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoomStateEnum {
    EMPTY("空置"), RESERVE("已预定"), CHECK_IN("已入住"), STOP("已停用");

    public final String desc;
}
