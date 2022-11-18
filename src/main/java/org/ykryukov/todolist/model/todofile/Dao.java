package org.ykryukov.todolist.model.todofile;

public interface Dao<T> {
	T getById(Integer id);
	
	Integer create(T todo);
	
	void delete(T todofile);
}
