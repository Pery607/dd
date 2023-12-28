package Db.Mapper;

import Db.Model.LessonEntity;
import Model.Lessons;


public class LessonsFromDBMapper {
    public static Lessons map(LessonEntity lesson){
        return new Lessons(lesson.getLessonName(),lesson.getExerciseName(),lesson.getType(),lesson.getMaxPoint());
    }
}
