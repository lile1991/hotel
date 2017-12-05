package com.hotel.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo<T> {
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    private int state;
    private T data;
    private String msg;

    public static <T> ResultVo<T> success(T data, String msg) {
        return new ResultVo<>(SUCCESS, data, msg);
    }

    public static <T> ResultVo<T> success(T data) {
        return success(data, null);
    }

    public static <T> ResultVo<T> fail(T data, String msg) {
        return new ResultVo<>(FAIL, data, msg);
    }
}
