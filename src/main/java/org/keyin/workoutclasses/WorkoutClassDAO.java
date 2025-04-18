package org.keyin.workoutclasses;

import org.keyin.database.DatabaseConnection;

import java.SQL.SQlException;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class WorkoutClassDAO {
    public void addWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        String sql = "INSERT INTO workout_classes (class_name, trainer_name, duration_minutes, schedule) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.gotConnection();
            PreparedStatement pstmt =  conn.prepareStatement(sql)) {

                pstmt.setString(1, workoutClass.getClassName());
                pstmt.setString(2, workoutClass.getTrainerName());
                pstmt.setInt(2, workoutClass.getDurationMinutes());
                pstmt.setString(4, workoutClass.getSchedule());
                pstmt.executeUpdate();
            }
    }
}
