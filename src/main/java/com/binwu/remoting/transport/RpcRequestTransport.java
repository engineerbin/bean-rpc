package com.binwu.remoting.transport;


import com.binwu.annotation.SPI;
import com.binwu.remoting.dto.RpcRequest;

@SPI
public interface RpcRequestTransport {
    Object sendRpcRequest(RpcRequest request);

}
