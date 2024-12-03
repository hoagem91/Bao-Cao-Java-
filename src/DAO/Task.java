/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.time.LocalDateTime;
/**
 *
 * @author Lenovo
 */
public class Task {
    private int id;
    private String name;
    private String description;
    private String createDate;
    private String deadline;

    // Constructor
    public Task(int id, String name, String description, String createDate, String deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createDate = createDate;
        this.deadline = deadline;
    }

    // Empty Constructor
    public Task() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Task {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", deadline=" + deadline +
                '}';
    }
}
