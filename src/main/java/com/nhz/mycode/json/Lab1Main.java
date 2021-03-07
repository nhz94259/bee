package com.nhz.mycode.json;

import com.google.gson.Gson;
import com.nhz.mycode.completed.util.BeanUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Lab1Main {

    private static Gson gson = new Gson();

    public static void main(String[] args) throws Exception {
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("name", "nahongze");
        jsonMap.put("age", "26");
        long start = System.currentTimeMillis();
        List<Lab1Main> returnList = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            returnList.add(BeanUtils.map2Bean(jsonMap, Lab1Main.class));
        }
        long end = System.currentTimeMillis();
        System.out.println("反射："+(end-start));

        long start2 = System.currentTimeMillis();
        List<Lab1Main> returnList2 = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            returnList2.add(gson.fromJson(gson.toJson(jsonMap), Lab1Main.class));
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Gson："+(end2-start2));
        System.out.println("结论：反射获取的方式更加直接！");
        //1000万数据
        //反射：1871
        //gson：14410
    }

}
