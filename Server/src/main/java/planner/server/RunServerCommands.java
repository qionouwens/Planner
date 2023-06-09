package planner.server;

import planner.commons.*;
import planner.commons.helper.DateConversion;
import planner.database.controller.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class RunServerCommands {
    public static void main(String[] args){
        TrainingPart trainingPart = new TrainingPart(0, 1300, "8:00");
        TrainingPart trainingPart1 = new TrainingPart(0, 1300, "8:00");
        Training training = new Training(1, "Running", "2x8'", DateConversion.getDate(2023, 6, 5), List.of(trainingPart, trainingPart1));
        TrainingDBController.addTraining(training);
    }
}
