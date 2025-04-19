package org.keyin.workoutclasses;

import java.sql.SQLException;
import java.util.List;

public class WorkoutClassService {
    // Inject in the DAO to be able to use it in the service
    private final WorkoutClassDAO workoutDAO = new WorkoutClassDAO();

    public void addWorkoutClass (WorkoutClass workoutClass) throws SQLException {
        workoutDAO.addWorkoutClass (workoutClass);
    }

    public List<WorkoutClass> getAllWorkoutClasses() throws SQLException {
        return workoutDAO.getAllWorkoutClasses();
    }

}
