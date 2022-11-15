package org.ykryukov.todolist.model.todo;

import java.util.List;

public interface Dao<T> {
	
	List<T> getAll();
	
	T getById(Integer id);
	
	void update(T todo);
	
	Integer create(T todo);
	
	void deleteById(Integer id);
}
