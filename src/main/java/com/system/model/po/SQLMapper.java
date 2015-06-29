package com.system.model.po;

import java.util.ArrayList;
import java.util.List;

import com.system.model.vo.TableVO;




/**
 * 进行sql标记替换的对象
 * @author admin
 *	2014-4-28 上午9:45:07
 */
public class SQLMapper implements java.io.Serializable{
	private static final long serialVersionUID = -8192066405275912218L;
	private List<TableVO> tables ;
	private StringBuffer sqlSelf ;
	private String selectSql ;
/*	private static final  String[][] object={
		new String[]{"#ISNULL"," ? is null "}
		,new String[]{"#NOTNULL"," ? is not null "}
		,new String[]{"&"," and "}
		,new String[]{"\\|\\|"," or "}
		,new String[]{"#gt"," > "}
		,new String[]{"#lt"," < "}
		,new String[]{"#this","?"}
		,new String[]{"#sql",""}
	};*/

	/**
	 * 添加表，会自动生成表的别名，表的别名是表名的小写加上此表在当前sql中的序列，
	 * 例如：b2b_table 则生成，b2b_table0,如果有多个相同的b2b_table，则会生成b2b_table0，b2b_table1，b2b_table2...
	 * @param tableName 表名
	 * @return
	 * SQLMapper
	 */
	public  SQLMapper addTable(String tableName) {
		if(null == tables) tables = new ArrayList<TableVO>();
		int i=0;
		for (TableVO t : tables) {
			if(t.getName().equals(tableName)) i++;
		}
		addTable(tableName,tableName.toLowerCase()+ i);
		return this;
	}
	/**
	 * 添加表，表的别名自定义
	 * @param tableName 表名
	 * @param alias 表的别名
	 * @return
	 * SQLMapper
	 */
	public  SQLMapper addTable(String tableName, String alias)  {
		if(tableName.trim().length() == 0) return this;
		if(alias.trim().length() == 0) addTable(tableName);
		TableVO t = new TableVO(); 
		t.setName(tableName);
		t.setAlias(alias);
		//setColumnDefault(t);
		tables.add(t);
		return this;
	}
	/**
	 * 添加sql片段，与原sql进行and操作
	 * @param sql
	 * @return
	 * SQLMapper
	 */
	public  SQLMapper andSql(String sql) {
		if(null == sqlSelf) sqlSelf =new StringBuffer();
		if(sql.trim().length()==0) return this;
		sqlSelf.append(" and (").append(sql).append(") ");
		return this;
	}
	/**
	 * 添加sql片段，与原sql进行or操作
	 * @param sql
	 * @return
	 * SQLMapper
	 */
	public  SQLMapper orSql(String sql) {
		if(null == sqlSelf) sqlSelf =new StringBuffer();
		if(sql.trim().length()==0) return this;
		sqlSelf.append(" or (").append(sql).append(") ");
		return this;	
	}
/*	public String toRunSQL() {
		StringBuffer sf = new StringBuffer();
		sf.append(selectSql);
		//组织数据表
		for(int i=0,n=tables.size();i<n;i++){
			sf.append(tables.get(i).getName()).append(" as ").append(tables.get(i).getAlias());
			if(n > 1 && i < n-1)
				sf.append(" , ");
		}
		
		//组合数据权限限制表的sql
		StringBuffer sf2 = new StringBuffer();
		sf2.append(" where 1=1");
		System.out.println("Thread:"+Thread.currentThread().getName()+";tables.size="+tables.size());
		for(int i=0,n=tables.size();i<n;i++){
			List<ColumnVO> cList =  tables.get(i).getDataColumn();
			for(int j=0,k=cList.size();j<k;j++){
				sf2.append(" and ").append(role2Sql(cList.get(j).getRole(),tables.get(i).getAlias(),cList.get(j))) ;
			}
		}
		sf.append(sf2);
		if(sqlSelf.toString().trim().length()>0)
			sf.append(sqlSelf);
		tables = null;
		sqlSelf = null;
		selectSql = null;
		return sf.toString();
	}*/
	/**
	 * 添加select的sql片段，sql片段中的字段信息，由自己指定
	 * @param sql
	 * @return
	 * SQLMapper
	 */
	public SQLMapper addSelectSQL(String sql) {
		selectSql = sql;
		return this;
	}
	/**
	 * 获取所有的表对象
	 * @return
	 * List<TableVO>
	 */
	public List<TableVO> getTables() {
		return tables;
	}
	/**
	 * 获取自定义的条件sql片段
	 * @return
	 * StringBuffer
	 */
	public StringBuffer getSqlSelf() {
		return sqlSelf;
	}
	/**
	 * 获取select的sql片段
	 * @return
	 * String
	 */
	public String getSelectSql() {
		return selectSql;
	}

}