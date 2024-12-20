/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DAO.Task;
import database.Connect;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Lenovo
 */
public class TaskDAO {
     private final Connect connect;

    public TaskDAO() {
        connect = new Connect(); // Lớp Connect chịu trách nhiệm quản lý kết nối
    }

    // Phương thức lấy danh sách task từ cơ sở dữ liệu
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM TASKLIST";

        try (Connection con = connect.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Task task = new Task(
                    rs.getInt("taskID"),
                    rs.getString("taskName"),
                    rs.getString("taskDesc"),
                    rs.getString("createDate"),
                    rs.getString("deadLine")
                );
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }
    /**
 * Adds a new task to the database.
 *
 * @param task The Task object to be added.
 * @return true if the task was successfully added, false otherwise.
 */
    public boolean addTask(Task task) {
        String sql = "INSERT INTO TASKLIST (TASKID, TASKNAME, TASKDESC, CREATEDATE, DEADLINE) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, task.getId());
            stmt.setString(2, task.getName());
            stmt.setString(3, task.getDescription());
            stmt.setString(4, task.getCreateDate());
            stmt.setString(5, task.getDeadline());

            // Execute the INSERT statement and check if a row was inserted
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding task: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean isTaskExist(int taskId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM TASKLIST WHERE TASKID = ?";
        try (Connection conn = new Connect().getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, taskId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Trả về true nếu ID tồn tại
                }
            }
        }
        return false;
    }
     // Phương thức xóa Task dựa trên TASKID
    public boolean deleteTask(int taskId) throws SQLException {
        if (!isTaskExist(taskId)) {
            return false; // Trả về false nếu task không tồn tại
        }

        String sql = "DELETE FROM TASKLIST WHERE TASKID = ?";
        try (Connection conn = new Connect().getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, taskId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu xóa thành công
        }
    }
    // Phương thức sửa Task dựa trên TASKID
    public boolean updateTask(Task task) {
        String sql = "UPDATE TASKLIST SET TASKNAME = ?, TASKDESC = ?, CREATEDATE = ?, DEADLINE = ? WHERE TASKID = ?";
        try (Connection conn = connect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getCreateDate());
            stmt.setString(4, task.getDeadline());
            stmt.setInt(5, task.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật task: " + e.getMessage());
            return false;
        }
    }
    
    // Phương thức tìm kiếm công việc theo ID
    public ResultSet searchTaskById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM TASKLIST WHERE TASKID = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, id);
        return pstmt.executeQuery();
    }

}
