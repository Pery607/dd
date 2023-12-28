package Model;

import com.j256.ormlite.field.DatabaseField;

public class StudentLessons {
    private String studentName;

    private String lessonName;

    private String exerciseName;

    private Exercise_Type type;

    private int point;


    public StudentLessons(String studentName, String lessonName, String exerciseName, Exercise_Type type, int point) {
        this.studentName = studentName;
        this.lessonName = lessonName;
        this.exerciseName = exerciseName;
        this.type = type;
        this.point = point;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getLessonName() {
        return lessonName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public Exercise_Type getType() {
        return type;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "StudentLessons{" +
                "studentName='" + studentName + '\'' +
                ", lessonName='" + lessonName + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", type=" + type +
                ", point=" + point +
                '}';
    }
}
