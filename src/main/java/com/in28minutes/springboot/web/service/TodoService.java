package com.in28minutes.springboot.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.web.model.Todo;

@Component
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "Ramesh", "Learn Spring MVC", new Date(),
                false));
        todos.add(new Todo(2, "Ramesh", "Learn Struts", new Date(), false));
        todos.add(new Todo(3, "Ramesh", "Learn Hibernate", new Date(),
                false));
    }

    
    public Todo retrieveTodo(int id)
    {
		for(Todo t : todos)
		{
			if(t.getId().equals(id))
			{
				return t;
			}
		}
    	
    	return null;
    	
    }
    
    public void updateTodo(Todo t)
    {
//    	deleteTodo(t.getId());
//    	addTodo(t.getUser(),t.getDesc(),new Date(), false);
    	todos.remove(t);
		todos.add(t);
    	
    }
    
    
    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equals(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public void addTodo(String name, String desc, Date targetDate,
            boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
}