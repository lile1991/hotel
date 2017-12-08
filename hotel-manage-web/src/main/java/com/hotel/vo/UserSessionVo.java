package com.hotel.vo;

import lombok.Data;

@Data
public class UserSessionVo {
    public static final String SESSION_KEY = "user.session.key";

    private String token;
}
