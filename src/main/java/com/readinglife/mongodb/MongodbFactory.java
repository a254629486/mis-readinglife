 /**
 * <b>项目名：</b>b2b-cps-service<br/>
 * <b>包名：</b>com.readinglife.b2b.cps.mongodb<br/>
 * <b>文件名：</b>MongodbFactory.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年11月22日-下午1:29:50<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.readinglife.mongodb;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.readinglife.tools.properties.JdbcProperties;


 /**
 * <b>类名称：</b>MongodbFactory<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年11月22日 下午1:29:50<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public class MongodbFactory {

	private static Mongo mg = null;
	private static DB db;

	public synchronized static DB getDB() {
			try {
				if (null == mg) {
					mg = new Mongo(JdbcProperties.get("mongodb.url"),Integer.valueOf(JdbcProperties.get("mongodb.port")));
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (MongoException e) {
				e.printStackTrace();
			}
			// 获取 DB；如果默认没有创建，mongodb会自动创建
			if (null == db) {
				db = mg.getDB(JdbcProperties.get("mongodb.keydb"));
			}
		return db;
	}
	
	public static void destory() {
		if (mg != null)
			mg.close();
		mg = null;
		db = null;
	}
 
}
