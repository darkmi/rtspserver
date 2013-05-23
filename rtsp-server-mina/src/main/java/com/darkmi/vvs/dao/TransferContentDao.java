package com.darkmi.vvs.dao;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.mongodb.MongodbDao;

import com.darkmi.vvs.rs.entity.TransferContent;


@Component
public class TransferContentDao extends MongodbDao<TransferContent, ObjectId> {

}
