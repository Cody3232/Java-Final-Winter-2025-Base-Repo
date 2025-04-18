package org.keyin.workoutclasses;

import java.sql.SQLException;

public class WorkoutClassService {
    // Inject in the DAO to be able to use it in the service
    private WorkoutClassDAO workoutDAO WorkoutClassDAO = new WorkoutClassDAO;

    public void addWorkoutClass (WorkoutClass workoutClass) throws SQLException {
        WorkoutClassDAO.addWorkoutClass (workoutClass);
    }

}
