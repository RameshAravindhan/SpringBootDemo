package com.SpringBoot.Demo.FirstProject.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.SpringBoot.Demo.FirstProject.Services.Services;
import com.in28minutes.springboot.web.model.Todo;
import com.in28minutes.springboot.web.service.TodoService;

@Controller
@ComponentScan(value = "com.in28minutes.springboot.web.service")
@SessionAttributes("name1")
public class TodoController {
	@Autowired
	TodoService todoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value = "/todolist", method = RequestMethod.GET)
//	@ResponseBody
	public String displayTodo(ModelMap model) {

		String name = extracted(model);	
		model.put("todoDisplay",todoService.retrieveTodos(getLoggedInUserName()));
		
		return "todolist1";
	}

	private String extracted(ModelMap model) {
		return (String) model.get("name1");
	}
	
	
	@RequestMapping(value = "/addtodo", method = RequestMethod.GET)
//	@ResponseBody
	public String addTodoDisplay(ModelMap model) {
		model.addAttribute("todo",new Todo(0,extracted(model),"", new Date(), false));
		return "addTodo";
	}
	
	
	@RequestMapping(value = "/addtodo", method = RequestMethod.POST)
//	@ResponseBody
	public String addTodoAfter(ModelMap model,@Valid Todo todo, BindingResult result) {
		if(result.hasErrors())
		{
			return "addTodo";
		}
		
		todoService.addTodo(extracted(model), todo.getDesc(), new Date(), false);
		return "redirect:/todolist";
	}
	
	@RequestMapping(value = "/deleteList", method = RequestMethod.GET)
//	@ResponseBody
	public String deleteTodos(ModelMap model,@RequestParam int id) {
		todoService.deleteTodo(id);
		return "redirect:/todolist";
	}
	
	@RequestMapping(value = "/updateList", method = RequestMethod.GET)
//	@ResponseBody
	public String updateTodosView(ModelMap model,@RequestParam int id) {
		Todo t = todoService.retrieveTodo(id);
	//	model.addAttribute("todo",new Todo(0,(String) model.get("name1"),"", new Date(), false));
		model.put("todo", t);
		return "updateTodo";
	}
	
	@RequestMapping(value = "/updateList", method = RequestMethod.POST)
//	@ResponseBody
	public String updateTodos(ModelMap model,Todo todo, BindingResult result) {
		todoService.updateTodo(todo);
		return "redirect:/todolist";
	}

	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

	
	
	


}
