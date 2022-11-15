package org.ykryukov.todolist.model.todofile;

public interface Dao<T> {
	T getById(Integer id);
	
	void create(T todo);
	
	void delete(T todofile);
}
