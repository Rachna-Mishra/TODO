package com.myservice.todo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myservice.todo.model.Todo; 
import com.myservice.todo.model.TodoResponse;
import com.myservice.todo.service.TodoService;
@Controller
public class MyController {
	static Logger logger=LoggerFactory.getLogger(MyController.class);
	@Autowired
	private TodoService todoService;

	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public @ResponseBody TodoResponse showTodos(@RequestParam String name) {
		List<Todo> list = null;
		TodoResponse response=null;
		try {
		list=todoService.getTodosByUser(name);
		response=new TodoResponse("Success","Todo list successfully fetched from databse",list);
		}
		catch(Exception e)
		{
			logger.error("Exception while getting the list from database: "+e.getMessage());
			response=new TodoResponse("Failure",e.getMessage(),null);
		}
		return response;
	}

	@RequestMapping(value = "/todo", method = RequestMethod.POST)
	public @ResponseBody TodoResponse addTodo(@RequestBody Todo todo) {
		TodoResponse response=null;
		try {
		todoService.addTodo(todo.getUserName(), todo.getDescription(), todo.getTargetDate(),todo.isDone());
		response=new TodoResponse("Success","Todo successfully added to the database",null);
		}catch(Exception e) {
			logger.error("Exception while adding the todo: "+e.getMessage());
			response=new TodoResponse("Failure",e.getMessage(),null);
		}
		return response;
	}

	@RequestMapping(value = "/todo", method = RequestMethod.DELETE)
	public @ResponseBody TodoResponse deleteTodo(@RequestParam long id) {
		TodoResponse response=null;
		try {
		todoService.deleteTodo(id);
		response=new TodoResponse("Success","Todo deleted successfully.",null);
		}
		catch(Exception e)
		{
			logger.error("Exception while deleting the entry from database.");
			response=new TodoResponse("Failure",e.getMessage(),null);
		}
		return response;
	}

	@RequestMapping(value = "/todo", method = RequestMethod.PUT)
	public @ResponseBody TodoResponse showUpdateTodoPage(@RequestParam long id,@RequestBody Todo todo) {
		TodoResponse response=null;
		try {
		todo.setId(id);
		todoService.updateTodo(todo);
		response=new TodoResponse("Success","Todo updated successfully.",null);
		}
		catch(Exception e) {
			logger.error("Exception while updating todo: "+e.getMessage());
			response=new TodoResponse("Failure",e.getMessage(),null);
		}
		return response;
	}

}
