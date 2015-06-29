package com.readinglife.tools.key;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.readinglife.mongodb.BeanDBObjectUtil;
import com.readinglife.mongodb.MongodbFactory;
import com.readinglife.mongodb.MongodbParam;
import com.readinglife.tools.properties.JdbcProperties;
import com.readinglife.tools.string.StringUtil;

public class KeyGenerate {
	/*public synchronized static String getKey(String key) {
		KeyModel keyModel = null;
		Date lastDate = null;
		Date currDate = new Date();
		//当前集合中存放的上次访问时的时间
		DBCollection ct = MongodbFactory.getDB().getCollection(
				JdbcProperties.get("mongodb.time"));
		//当前集合中存放生成主键方式的数据
		DBCollection c = MongodbFactory.getDB().getCollection(
				JdbcProperties.get("mongodb.GenerateKey"));
		try {
			DBObject ob = ct.findOne();
			if(ob!=null){
				lastDate = new Date(Long.parseLong(ob.get("time")+""));
			}
			ct.update(ob, BeanDBObjectUtil.convertBean2DBObject(new Time(currDate.getTime())));
			String[] param = { "key", key};
			 DBObject obj =  c.findOne(MongodbParam.toParam(param));
			 if(obj!=null){
				 String keyVal = String.valueOf(obj.get("key"));
				 String dateStyleVal =String.valueOf(obj.get("dateStyle"));
				 String sequenceVal = String.valueOf(obj.get("sequence"));
				 keyModel =new KeyModel(keyVal,dateStyleVal,sequenceVal);
			 }
			if (keyModel == null) {//如果库中没有当前正在查询的key 则默认创建
				keyModel = new KeyModel(key, "yyyyMMdd", "0000000");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		SimpleDateFormat df = null;
		String dateStyle = keyModel.getDateStyle();
		String sequence = keyModel.getSequence();

		int places = 0;//序列的位数
		if (!(dateStyle == null || "".equals(dateStyle))) {
			df = new SimpleDateFormat(dateStyle);
		}
		switch (dateStyle) {
		case "yyyyMMddhhmmss":
			if (!StringUtil.dateFormatToString(currDate, "yyyyMMddhhmmss")
					.equals(StringUtil.dateFormatToString(lastDate,
							"yyyyMMddhhmmss"))) {
				sequence = "0";
			}
			places = 4;
			break;
		case "yyyyMMdd":
			if (!StringUtil.dateFormatToString(currDate, "yyyyMMdd").equals(
					StringUtil.dateFormatToString(lastDate, "yyyyMMdd"))) {
				sequence = "0";
			}
			places = 7;
			break;
		default:
			places = 9;
			break;
		}
		String newSequence = String.valueOf(Integer.valueOf(sequence) + 1);
		while (newSequence.length() < places) {
			newSequence = "0" + newSequence;
		}
		keyModel = new KeyModel(key, dateStyle, newSequence);
		String[] param = { "key", keyModel.getKey() };
		DBCursor cur = c.find(MongodbParam.toParam(param));
		try {
			if (!cur.hasNext()) {
				c.insert(BeanDBObjectUtil.convertBean2DBObject(keyModel));
			} else {
				c.update(cur.next(),
						BeanDBObjectUtil.convertBean2DBObject(keyModel));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MongodbFactory.destory();
		}

		String result = keyModel.getKey()
				+ (df != null ? df.format(currDate) : "")
				+ keyModel.getSequence();
		return result;
	}*/
	
}
