package com.readinglife.framework.example;

public class SqlKeyCriterion {
	private String namespace;

	private String getNamespace() {
		return namespace;
	}
	
	public SqlKeyCriterion dao(){
		this.namespace=this.namespace+"Mapper";
		return this;
	}
	public SqlKeyCriterion dao(String daoName){
		namespace=namespace.substring(0, namespace.lastIndexOf("."));
		this.namespace=this.namespace+"."+daoName;
		return this;
	}
	
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	public String sqlId(String sqlId){
		return namespace+"."+sqlId;
	}
	
	
	public String selectByPrimaryKey(){
		return namespace+".selectByPrimaryKey";
	}
	public String selectByExample(){
		return namespace+".selectByExample";
	}
	public String deleteByPrimaryKey(){
		return namespace+".deleteByPrimaryKey";
	}
	public String deleteByExample(){
		return namespace+".deleteByExample";
	}
	public String insert(){
		return namespace+".insert";
	}
	public String update(){
		return namespace+".update";
	}
	public String insertSelective(){
		return namespace+".insertSelective";
	}
	public String countByExample(){
		return namespace+".countByExample";
	}
	public String updateByExampleSelective(){
		return namespace+".updateByExampleSelective";
	}
	public String updateByExample(){
		return namespace+".updateByExample";
	}
	public String updateByPrimaryKeySelective(){
		return namespace+".updateByPrimaryKeySelective";
	}
	public String updateByPrimaryKey(){
		return namespace+".updateByPrimaryKey";
	}
	
	
}
