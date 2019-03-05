package de.novatec.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.novatec.model.TodoItem;


@Repository
public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {}