package com.nhz.mycode.completed.mongo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Document(collection = "viki_collection")
public class CouponLottery {
    @Id
    private String id;
    /**
     * [{
     * couponName:300,
     * activityId:7070,
     * couponDesc: desc
     * }]
     */
    private List<Map<String, String>> couponList;
    private String name;
    private String ip;
    /**
     * 用户抽奖概率map
     */
    private String userProbabilityMap;
}
