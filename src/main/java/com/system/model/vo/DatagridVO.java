package com.system.model.vo;

import java.util.ArrayList;

public class DatagridVO {
	private Integer total;
	private Object rows;
	public DatagridVO(){
		
	}
	public DatagridVO(Integer total,Object rows){
		this.total=total;
		this.rows=rows;
	}
	public DatagridVO(Object rows){
		this.total=((ArrayList<Object>)rows).size();
		this.rows=rows;
	}
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	
}
