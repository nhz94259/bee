package com.nhz.mycode.completed.arthas;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;


/**
 * -XX:MetaspaceSize=23m -XX:MaxMetaspaceSize=23m -verbose:class
 *
 * jmap -histo:live pid|more 无果
 *
 * jstat -gcutil 4312  3000
 */

public class MetaSpaceLab {

    public static void main(String[] args) {
        User user = new User();
        user.setUserAge("15");
        user.setUserId("id-1");
        user.setUserPhone("1xx-xxxx-xxxx");
        user.setUserSex("1");
        while (true) {
            buildData_Right(user);
        }
    }

    /**
     * 返回Json字符串.驼峰转蛇_
     * @param bean 实体类
     */
    public static String buildData_Wrong(Object bean) {
        try {
            SerializeConfig config = new SerializeConfig();
            config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
            return JSON.toJSONString(bean, config);
        } catch (Exception e) {
            return null;
        }
    }

    static SerializeConfig config = new SerializeConfig();
    static {
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
    }
    public static String buildData_Right(Object bean) {
        try {
            config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
            return JSON.toJSONString(bean, config);
        } catch (Exception e) {
            return null;
        }
    }




}
