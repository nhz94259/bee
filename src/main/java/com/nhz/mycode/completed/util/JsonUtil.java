package com.nhz.mycode.completed.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.*;

@Slf4j
public class JsonUtil {

    public static String Pretty(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonObject);
    }

    /**
     * @des  依据json字符串返回Map对象List
     * @on_conditional 根节点数为1
     *
    {
    "OneRoot": [{
    "key1": "value1",
    "key2": "value2"
    }, {
    "key1": "value2",
    "key2": "value2"
    }, {
    "key1": "value3",
    "key2": "value3"
    }]
    }
     *
     * @param json
     * @return
     */
    public static List<Map<String, Object>> toListMap(String json ){

        List<Map<String, Object>> ListMap = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = null;
        try {
            root = mapper.readTree(json);
        } catch (IOException e) {
            log.error( "msg:{}", e);
        }
        Assert.notNull(root, "root must not be null");
        JsonNode tempArray = root.findValue((String) getJsonRootName(json));
        Iterator<JsonNode> iter = tempArray.iterator();
        while (iter.hasNext()) {
            JsonNode neutronObjNode =iter.next();
            Map<String, Object> ob = toMap(neutronObjNode.toString());
            ListMap.add(ob);
        }
        return ListMap;

    }

    /**
     * @des 依据json字符串返回Map对象
     * @on_conditional 多根
     * @param json
     * @return
     */
    public static Map<String,Object> toMap(String json){
        return  toMap( parseJsonToJsonObject(json));
    }


    /**
     * 返回单个
     * 将JSONObjec对象转换成Map-List集合
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(JsonObject json){
        Map<String, Object> map = new HashMap<String, Object>();
        Set<Map.Entry<String, JsonElement>> entrySet = json.entrySet();
        for (Iterator<Map.Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){
            Map.Entry<String, JsonElement> entry = iter.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof JsonArray)
                map.put((String) key, toList((JsonArray) value));
            else if(value instanceof JsonObject)
                map.put((String) key, toMap((JsonObject) value));
            else if(value instanceof JsonNull){
                //null处理
                map.put((String) key, "");
            }else  {
                map.put((String) key,value);
            }
        }
        return map;
    }

    /**
     * 将JSONArray对象转换成List集合
     * @param json
     * @return
     */
    public static List<Object> toList(JsonArray json){
        List<Object> list = new ArrayList<Object>();
        for (int i=0; i<json.size(); i++){
            Object value = json.get(i);
            if(value instanceof JsonArray){
                list.add(toList((JsonArray) value));
            }
            else if(value instanceof JsonObject){
                list.add(toMap((JsonObject) value));
            }
            else{
                list.add(value);
            }
        }
        return list;
    }




    /**
     * @param json
     * @return json rootName or json RootList
     *
     * */
    public static Object getJsonRootName(String json){

        JsonObject root = parseJsonToJsonObject(json);
        Set<Map.Entry<String, JsonElement>> entrySet = root.entrySet();
        if(entrySet.size() == 0)
            return null;
        if(entrySet.size() == 1){
            for (Iterator<Map.Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){
                Map.Entry<String, JsonElement> entry = iter.next();
                return  entry.getKey();
            }
        }
        List<String> rootNameList = new ArrayList<>();

        for (Iterator<Map.Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){
            Map.Entry<String, JsonElement> entry = iter.next();
            rootNameList.add(entry.getKey()) ;
        }

        return rootNameList;
    }


    /**
     * 获取JsonObject
     * @param json
     * @return
     */
    public static JsonObject parseJsonToJsonObject(String json){
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = parser.parse(json).getAsJsonObject();
        return jsonObj;
    }
    /**
     * 获取JsonArry
     * @param json
     * @return
     */
    public static JsonArray parseJsonToJsonArray(String json){
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(json).getAsJsonArray();
        return jsonArray;
    }
    public static Object jsonPath(String json,String path){
        return   JsonPath.read(json, path);
    }

    public static Boolean jsonPathExist(String json,String path){
        try{
            JsonPath.read(json, path);
        }catch (Exception e){
           // log.warn("found none in {}",path);
            return false;
        }
        return true;
    }


}
