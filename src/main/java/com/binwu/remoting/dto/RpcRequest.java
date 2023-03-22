package com.binwu.remoting.dto;

import jdk.internal.org.objectweb.asm.commons.SerialVersionUIDAdder;
import lombok.*;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = -6143023566771309065L;
    private String requestId;
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private String version;
    private String group;

    public String getRpcServiceName() {
        return interfaceName + group + version;
    }
}
