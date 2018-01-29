package com.hotel.user.constant;

import com.hotel.enums.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResourceModuleConstant implements EnumInterface {
    USER("用户"),
    ROOM("房间");

    public final String desc;
}
