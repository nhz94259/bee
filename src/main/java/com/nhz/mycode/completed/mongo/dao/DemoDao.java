package com.nhz.mycode.completed.mongo.dao;

import com.nhz.mycode.completed.mongo.model.CouponLottery;

import java.util.List;

public interface DemoDao {

    void saveDemo(CouponLottery demoEntity);

    void removeDemo(String id);

    void updateDemo(CouponLottery demoEntity);

    CouponLottery findDemoById(String id);

    List<CouponLottery> findAll();
}
