package com.nhz.mycode.completed.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.util.Map;

public class InvocationHandlerviaLambdaExpressionsProxy {

    private static Logger logger = LoggerFactory.getLogger(InvocationHandlerviaLambdaExpressionsProxy.class);

    public static void main(String[] args) {
        Map map = (Map) Proxy.newProxyInstance(
                JdkProxyHandler.class.getClassLoader(),
                new Class[] { Map.class },
                (proxy, method, methodArgs) -> {
                    if (method.getName().equals("get")) {
                        return 42;
                    } else {
                        throw new UnsupportedOperationException(
                                "Unsupported method: " + method.getName());
                    }
                });
        logger.debug(map.get("35").toString());
        map.put("1", 1);
    }

}
