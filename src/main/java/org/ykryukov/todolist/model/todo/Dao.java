package org.ykryukov.todolist.model.todo;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
	
	List<T> getAll();
	
	T getById(int id);
	
	void update(T todo);
	
	void create(T todo);
	
	void deleteById(int id);
}
