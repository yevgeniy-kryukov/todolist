package org.ykryukov.todolist.model.todo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.ykryukov.todolist.model.todofile.TodoFile;

@Entity
@Table(schema = "todo", name = "todo")
public class Todo {

	@Column(name = "date_time_action")
	private Timestamp dateTimeAction;

	@Column(name = "text_action")
	private String textAction;

	@Column(name = "is_done")
	private boolean isDone;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@SequenceGenerator(name = "idSeqGen", sequenceName = "todo.todo_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idSeqGen")
	private Integer id;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "todo")
	@OrderBy("id DESC")
	private List<TodoFile> todoFiles;

	public List<TodoFile> getTodoFiles() {
		return todoFiles;
	}

	public void setTodoFiles(List<TodoFile> todoFiles) {
		this.todoFiles = todoFiles;
	}

	public Todo() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Todo)) return false;
		Todo todo = (Todo) o;
		return isDone == todo.isDone
				&& dateTimeAction.equals(todo.dateTimeAction)
				&& textAction.equals(todo.textAction)
				&& id.equals(todo.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Todo(Timestamp dateTimeAction, String textAction) {
		super();
		setDateTimeAction(dateTimeAction);
		setTextAction(textAction);
	}

	public Todo(Timestamp dateTimeAction, String textAction, boolean isDone, Integer id) {
		super();
		setDateTimeAction(dateTimeAction);
		setTextAction(textAction);
		setDone(isDone);
		setId(id);
	}

	public Timestamp getDateTimeAction() {
		return dateTimeAction;
	}

	public void setDateTimeAction(Timestamp dateTimeAction) {
		this.dateTimeAction = dateTimeAction;
	}

	public String getTextAction() {
		return textAction;
	}

	public void setTextAction(String textAction) {
		this.textAction = textAction;
	}

	public boolean getIsDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Todo [dateTimeAction=" + dateTimeAction + ", textAction=" + textAction + ", isDone=" + isDone + ", id="
				+ id + ", todoFiles=" + todoFiles + "]";
	}
}
