package com.readinglife.tools.key;

import java.io.Serializable;

public class KeyModel implements Serializable{
	private String key;
	private String dateStyle;
	private String sequence;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDateStyle() {
		return dateStyle;
	}
	public void setDateStyle(String dateStyle) {
		this.dateStyle = dateStyle;
	}
	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public KeyModel() {
		// TODO Auto-generated constructor stub
	}
	
	public KeyModel(String key, String dateStyle, String sequence) {
		super();
		this.key = key;
		this.dateStyle = dateStyle;
		this.sequence = sequence;
	}
	@Override
	public String toString() {
		return "KeyModel [key=" + key + ", dateStyle=" + dateStyle
				+ ", sequence=" + sequence + "]";
	}
	
}
