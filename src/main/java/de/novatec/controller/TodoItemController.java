package de.novatec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import de.novatec.model.TodoItem;
import de.novatec.repository.TodoItemRepository;

import java.util.Date;
import java.util.Optional;


@RestController
public class TodoItemController 
{
	@Autowired
	private TodoItemRepository repo;
		
	@GetMapping("/")
	Iterable<TodoItem> todo()
	{
		return repo.findAll();
	}
	
	@PostMapping("/{todo}")
	String addTodo(@PathVariable String todo)
	{
		repo.save(new TodoItem(todo, false, new Date()));
		return "added "+todo+" to todo list";
	}
	
	@DeleteMapping("/{id}")
	String deleteTodo(@PathVariable Long id)
	{
		Optional<TodoItem> todoOpt = repo.findById(id);
		if(todoOpt.isPresent())
		{
			TodoItem todo = todoOpt.get();
			repo.delete(todo);
			return "removed "+todo.getName()+" from todo list";
		}
		else
		{
			return "no todo item with that id";
		}
	}
}