package de.novatec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TodoItem 
{
	@Id
	@GeneratedValue
	long Id;
	
	String name;
	boolean isDone;
	
	protected TodoItem() {}
	
	public TodoItem(String name, boolean isDone)
	{
		this.name 	= name;
		this.isDone = isDone;
	}
	
	public String getName() {
		return name;
	}
	
	public long getId() {
		return Id;
	}
	
	public boolean getIsDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
}