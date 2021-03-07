package com.nhz.mycode.completed.mongo.dao;

import com.nhz.mycode.Application;
import com.nhz.mycode.completed.mongo.model.CouponLottery;
import com.mongodb.client.result.UpdateResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DemoDaoImplTest {
    protected static final String FIELD_ID = "_id";

   private static Logger LOGGER = LoggerFactory.getLogger(DemoDaoImplTest.class);

    @Autowired
    private DemoDao demoDao;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void saveDemo() {
        CouponLottery couponLottery = new CouponLottery();
        List<Map<String, String>> list = new ArrayList<>();

        Map map1 = new HashMap();
        map1.put("map1-a","a1");
        map1.put("map1-b","b1");
        map1.put("map1-c","c1");
        Map map2 = new HashMap();
        map2.put("map2-a","a2");
        map2.put("map2-b","b2");
        map2.put("map2-c","c2");
        list.add(map2);
        list.add(map1);
        couponLottery.setCouponList(list);
        couponLottery.setIp("10.172.131.13");
        couponLottery.setName("10name");
        demoDao.saveDemo(couponLottery);
    }

    @Test
    public void removeDemo() {
        demoDao.removeDemo("5fa4da312a24ff0c6b4cf547");
    }

    @Test
    public void updateDemo() {
        Query query = new Query();
        query.addCriteria(Criteria.where("ip").is("10name"));
        query.addCriteria(Criteria.where("name").is("10name"));
        CouponLottery couponLottery = mongoTemplate.findOne(query, CouponLottery.class);
        LOGGER.info(couponLottery.toString());
    }

    @Test
    public void findDemoById() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("10name").and("ip").is("10.172.131.13"));
        Update update = new Update();
        update.set("userProbabilityMap", "33");
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, CouponLottery.class);
        LOGGER.info(""+updateResult.getMatchedCount());
    }

    @Test
    public void findDemoByCondition() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("10name").and("ip").is("10.172.131.13"));
        CouponLottery couponLottery = mongoTemplate.findOne(query, CouponLottery.class);
        LOGGER.info(couponLottery.toString());
    }

    @Test
    public void findDemoAll() {
        List<CouponLottery> all = demoDao.findAll();
        all.stream().forEach(o -> LOGGER.info(o.toString()));
    }
}