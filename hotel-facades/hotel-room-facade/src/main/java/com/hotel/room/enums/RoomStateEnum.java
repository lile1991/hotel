package com.hotel.room.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoomStateEnum implements EnumInterface {
    EMPTY("空置"), RESERVE("已预定"), CHECK_IN("已入住"), STOP("已停用");

    public final String desc;
}
