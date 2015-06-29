package com.readinglife.tools.key;

import java.io.Serializable;
import java.util.Date;
import java.util.Timer;

public class Time implements Serializable {
	private long time;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	public Time(){}

	public Time(long time) {
		super();
		this.time = time;
	}

}
