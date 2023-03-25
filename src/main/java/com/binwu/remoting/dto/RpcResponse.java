package com.binwu.remoting.dto;

import com.binwu.enums.RpcResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RpcResponse<T> implements Serializable {

    private static final long serialVersionUID = -1651056556441330839L;
    private String requestId;
    private Integer code;
    private String message;
    private T data;

    public static <T> RpcResponse<T> success(T data, String requestId){
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(RpcResponseCodeEnum.SUCCESS.getCode());
        response.setMessage(RpcResponseCodeEnum.SUCCESS.getMessage());
        response.setRequestId(requestId);
        response.setData(data);
        return response;
    }

    public static <T> RpcResponse<T> fail(RpcResponseCodeEnum rpcResponseCodeEnum){
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(rpcResponseCodeEnum.getCode());
        response.setMessage(rpcResponseCodeEnum.getMessage());
        return response;
    }

}
