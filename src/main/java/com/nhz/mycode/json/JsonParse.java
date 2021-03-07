package com.nhz.mycode.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class JsonParse {

    private static Configuration configuration =
            Configuration.builder().options(Option.DEFAULT_PATH_LEAF_TO_NULL).build();

    /**
     * In case you need to read an other path as well this is not the way to
     * go since the document will be parsed every time you call JsonPath.read(...).
     * To avoid the problem you can parse the json first.
     * @param json
     * @param inputType
     * @param returnType
     */
    public static <V,T> List<T> toBeanList(String json, Class<V> inputType, Class<T> returnType)  {

        JsonPathProperty typeAnnotation = inputType.getAnnotation(JsonPathProperty.class);
        if(typeAnnotation.path().length>1){
            throw new IllegalStateException("Class annotation do not support multi path");
        }
        if(typeAnnotation != null){
            json =  JsonPath.parse(json, configuration).read(typeAnnotation.path()[0]).toString();
        }
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(json).getAsJsonArray();
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            String itemJson = array.get(i).getAsJsonObject().toString();
            returnList.add(toBean(itemJson, inputType, returnType));
        }
        return returnList;
    }

    public static <T> T toBean(String json, Class cls, Class<T> returnType) {
        Object document = configuration.jsonProvider().parse(json);
        Field[] fields = cls.getDeclaredFields();
        List<Map<String, String>> data = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        data.add(map);
        for (int j = 0; j < fields.length; j++) {
            JsonPathProperty annotation = fields[j].getAnnotation(JsonPathProperty.class);
            if (annotation == null) {
                continue;
            }
            String[] paths = annotation.path();
            String key = annotation.alias();
            if (StringUtils.isBlank(key)) {
                key = fields[j].getName();
            }
            String defaultValue = annotation.defaultValue();
            String value = jsonPathParse(document, paths, defaultValue);
            map.put(key, value);
        }
        return map2Bean(map, returnType);
    }

    /**
     * @param json input parameter
     * @param paths There may be multiple paths to parse param data
     * @param defaultValue The default value is returned when the value is empty,
     *                     and it is returned immediately when the value is not empty
     */
    private static String jsonPathParse(Object json ,String[] paths,String defaultValue){
        for (int i = 0; i < paths.length; i++) {
            Object value = JsonPath.parse(json, configuration).read(paths[i]);
            if (Objects.isNull(value)||StringUtils.isBlank(value.toString())) {
                continue;
            }
            return value.toString();
        }
        return defaultValue;
    }

    /**
     * @param <T> <K> map type
     * @param <V>
     * @param returnType  the type of return value
     */
    public static <T, K, V> T map2Bean(Map<K, V> map, Class<T> returnType) {
        T t = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(returnType);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            t = returnType.newInstance();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    Method setter = property.getWriteMethod();
                    setter.invoke(t, value);
                }
            }

        } catch (IntrospectionException |IllegalAccessException|
                InstantiationException| InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }
}
