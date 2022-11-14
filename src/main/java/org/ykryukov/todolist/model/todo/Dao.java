package org.ykryukov.todolist.model.todo;

import java.util.List;

public interface Dao<T> {
	
	List<T> getAll();
	
	T getById(int id);
	
	void update(T todo);
	
	T create(T todo);
	
	void deleteById(int id);
}
