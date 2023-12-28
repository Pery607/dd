package Db.Mapper;

import Db.Model.StudentLessonEntity;
import Model.StudentLessons;

public class StudentLessonsFromDBMapper {
    public static StudentLessons map(StudentLessonEntity student){
        return new StudentLessons(student.getStudentName(), student.getLessonName(), student.getExerciseName(),student.getType(),student.getPoint());
    }
}
