package org.keyin.workoutclasses;

import org.keyin.database.DatabaseConnection;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WorkoutClassDAO {
    public void addWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        String sql = "INSERT INTO workout_classes (class_name, trainer_name, duration_minutes, schedule) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt =  conn.prepareStatement(sql)) {

                pstmt.setString(1, workoutClass.getClassName());
                pstmt.setString(2, workoutClass.getTrainerName());
                pstmt.setInt(3, workoutClass.getDurationMinutes());
                pstmt.setString(4, workoutClass.getSchedule());
                pstmt.executeUpdate();
            }
    }

    public List<WorkoutClass> getAllWorkoutClasses() throws SQLException {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            WorkoutClass wc = new WorkoutClass(
                rs.getInt("class_id"),
                rs.getString("class_name"),
                rs.getString("trainer_name"),
                rs.getInt("duration_minutes"),
                rs.getString("schedule")
            );
            classes.add(wc);    
        }
        }
    return classes; 
    }

    public void updateWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        String sql = "UPDATE workout_classes " + "SET class_name = ?, trainer_name = ?,          duration_minutes = ?, schedule = ? " + "WHERE class_id =?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, workoutClass.getClassName());
                pstmt.setString(2, workoutClass.getTrainerName());
                pstmt.setInt(3, workoutClass.getDurationMinutes());
                pstmt.setString(4, workoutClass.getSchedule());
                pstmt.setInt(5, workoutClass.getClassId());
                pstmt.executeUpdate();
        }
    }

    public void deleteWorkoutClass(int classId) throws SQLException {
        String sql = "DELETE FROM workout_classes WHERE class_id = ? ";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, classId);
                pstmt.executeUpdate();
            }

    }

}
