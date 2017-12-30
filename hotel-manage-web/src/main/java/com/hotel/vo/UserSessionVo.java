package com.hotel.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserSessionVo implements Serializable {
    public static final String SESSION_KEY = "user.session.key";

    private Long id;
    private String credentials;
    private String ip;
}
