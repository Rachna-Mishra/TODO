package com.myservice.todo.model;

import java.util.List;

public class TodoResponse {
	String status;
	String message;
	List<Todo> data;
	public TodoResponse(String status, String message, List<Todo> data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	public TodoResponse() {
		// TODO Auto-generated constructor stub
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Todo> getData() {
		return data;
	}
	public void setData(List<Todo> data) {
		this.data = data;
	}
	
}
