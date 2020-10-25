package cn.xdl.ydma.util;

import java.io.Serializable;
import java.util.List;
public class YdmaResult<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String msg;
	private T data;
	public YdmaResult(int code, String msg, T data) {
		
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public YdmaResult() {
		super();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
