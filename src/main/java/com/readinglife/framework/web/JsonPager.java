package com.readinglife.framework.web;

import java.util.List;
import com.readinglife.tools.json.Jacksons;

/**
 * 
 * <b>类名称：</b>JsonPager<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年8月26日 上午9:25:52<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public class JsonPager <T>{
	private List<T> rows; 		//当页记录
	private Integer page=1;		//页号
	private Integer size=10;//每页记录数
	private Integer total=0;	//总记录数

	public JsonPager() {

	}
	
	/**
	  * <p>描述: 初始化自定义分页条数</p>  
	  * @param size 条数
	 */
	public JsonPager(Integer size) {
		this.size = size;
		this.forPage();
	}
	/**
	  *  <p>描述: 初始化自定义分页条数，和起始页</p>  
	  * @param page 起始页
	  * @param size 条数
	 */
	public JsonPager(Integer page,Integer size) {
		this.page = page;
		this.size = size;
		this.forPage();
	}
	/**
	  *  <p>描述: 初始化自定义分页条数，和起始页</p>  
	  * @param page 起始页
	  * @param size 条数
	 */
	public JsonPager(Integer page,Integer size,Integer total) {
		this.page = page;
		this.size = size;
		this.total = total;
		this.forPage();
	}
	/**
	 * @Title toJsonString
	 * @Description TODO 将page转换成json string
	 * @return jsonList
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年8月26日-下午3:52:46
	 * @update 
	 */
	public String toJsonString(String datetimeFormat) {
		return Jacksons.json().setDateFormate(datetimeFormat).fromObjectToJson(this);
	}
	
	private void forPage(){
		this.page =  this.getPage()>1?((this.getPage()-1)*this.getSize()):0;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
		this.forPage();
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
		this.forPage();
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
}
