package org.ykryukov.todolist.model.todofile;

public interface Dao<T> {
	T getById(int id);
	
	void create(T todo);
	
	void delete(T todofile);
}
