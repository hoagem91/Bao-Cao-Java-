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
     public boolean addTask(Task task) {
        String sql = "INSERT INTO Task (TASKID, TASKNAME, TASKDESC, STATUS, CREATEDATE, DEADLINE) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = new Connect().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, task.getId());
            stmt.setString(2, task.getName());
            stmt.setString(3, task.getDescription());
            stmt.setString(4, task.getStatus());
            stmt.setTimestamp(5, Timestamp.valueOf(task.getCreateDate()));
            stmt.setTimestamp(6, Timestamp.valueOf(task.getDeadline()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
     // Phương thức xóa Task dựa trên TASKID
    public boolean deleteTask(int taskId) {
        String sql = "DELETE FROM Task WHERE TASKID = ?";
        try (Connection conn = new Connect().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, taskId);
            
            // Thực thi lệnh DELETE và kiểm tra xem có xóa được không
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa task: " + e.getMessage());
            return false;
        }
    }
    // Phương thức sửa Task dựa trên TASKID
    public boolean updateTask(Task task) {
        String sql = "UPDATE Task SET TASKNAME = ?, TASKDESC = ?, STATUS = ?, CREATEDATE = ?, DEADLINE = ? WHERE TASKID = ?";
        try (Connection conn = new Connect().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getStatus());
            stmt.setTimestamp(4, Timestamp.valueOf(task.getCreateDate()));
            stmt.setTimestamp(5, Timestamp.valueOf(task.getDeadline()));
            stmt.setInt(6, task.getId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật task: " + e.getMessage());
            return false;
        }
    }
}
