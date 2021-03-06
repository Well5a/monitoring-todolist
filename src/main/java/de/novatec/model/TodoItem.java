package de.novatec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TodoItem 
{
	@Id
	@GeneratedValue
	long Id;
	
	String name;
	boolean isDone;
	Date creationDate;

	
	protected TodoItem() {}
	
	public TodoItem(String name, boolean isDone, Date creationDate)
	{
		this.name 	= name;
		this.isDone = isDone;
		this.creationDate = creationDate;
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

	public Date getCreationDate() { return creationDate; }

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
}