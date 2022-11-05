package org.ykryukov.todolist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.ykryukov.todolist.model.todo.Todo;

@Entity
@Table(schema = "todo", name = "todo_file")
public class TodoFile {
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "todo_id", referencedColumnName = "id", updatable = false, nullable = false)
	private Todo todo;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "file_description")
	private String fileDescription;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@SequenceGenerator(name = "idSeqGen", sequenceName = "todo.todo_file_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idSeqGen")
	private int id;

	public TodoFile() {
	}

	public TodoFile(Todo todo, String fileName, String filePath, String fileDescription) {
		setTodo(todo);
		setFileName(fileName);
		setFilePath(filePath);
		setFileDescription(fileDescription);
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
