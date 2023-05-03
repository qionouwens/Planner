package planner.commons;

import java.util.*;

public class CalendarItem {
    private int id;
    private String title;
    private GregorianCalendar date;
    private String startTime;
    private String endTime;
    private String colour;
    private List<Todo> todoList;

    public CalendarItem() {
    }

    public CalendarItem(int id, String title, GregorianCalendar date, String startTime, String endTime, String colour, List<Todo> todoList) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.colour = colour;
        this.todoList = todoList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

    public void addTodo(Todo todo) {
        this.todoList.add(todo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarItem that = (CalendarItem) o;
        return id == that.id && Objects.equals(title, that.title)
                && Objects.equals(date, that.date)
                && Objects.equals(startTime, that.startTime)
                && Objects.equals(endTime, that.endTime)
                && Objects.equals(colour, that.colour)
                && Objects.equals(todoList, that.todoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, startTime, endTime, colour, todoList);
    }

    @Override
    public String toString() {
        return "CalendarItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", colour='" + colour + '\'' +
                ", todoList=" + todoList +
                '}';
    }
}
