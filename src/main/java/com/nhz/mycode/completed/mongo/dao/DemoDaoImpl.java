package com.nhz.mycode.completed.mongo.dao;

import com.nhz.mycode.completed.mongo.model.CouponLottery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DemoDaoImpl implements DemoDao {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void saveDemo(CouponLottery demoEntity) {
        mongoTemplate.save(demoEntity);
    }

    @Override
    public void removeDemo(String id) {
        mongoTemplate.remove(id);
    }

    @Override
    public void updateDemo(CouponLottery demoEntity) {
        Query query = new Query(Criteria.where("id").is(demoEntity.getId()));

        Update update = new Update();

        mongoTemplate.updateFirst(query, update, CouponLottery.class);
    }

    @Override
    public CouponLottery findDemoById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        CouponLottery demoEntity = mongoTemplate.findOne(query, CouponLottery.class);
        return demoEntity;
    }

    @Override
    public List<CouponLottery> findAll() {
        return mongoTemplate.findAll(CouponLottery.class);
    }
}
