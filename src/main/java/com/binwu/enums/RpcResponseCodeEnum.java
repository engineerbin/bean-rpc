package com.binwu.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RpcResponseCodeEnum {
    SUCCESS(200, "The remote call is successful."),
    FAIL(500, "The remote call is failed.");

    private final int code;
    private final String message;
}
