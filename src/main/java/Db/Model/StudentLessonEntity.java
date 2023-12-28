package Db.Model;

import Model.Exercise_Type;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "studentLesson")
public class StudentLessonEntity {
    public static final String NAME_COLUMN = "studentName";
    @DatabaseField(generatedId = true)
    private long studentLessonId;
    @DatabaseField(canBeNull = false)
    private String studentName;
    @DatabaseField(canBeNull = false)
    private String lessonName;
    @DatabaseField(canBeNull = false)
    private String exerciseName;
    @DatabaseField(canBeNull = false)
    private Exercise_Type type;
    @DatabaseField(canBeNull = false)
    private int point;

    public StudentLessonEntity() {
    }

    public StudentLessonEntity(String studentName, String lessonName, String exerciseName, Exercise_Type type, int point) {
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
        return "StudentLessonEntity{" +
                "studentLessonId=" + studentLessonId +
                ", studentName='" + studentName + '\'' +
                ", lessonName='" + lessonName + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", type=" + type +
                ", point=" + point +
                '}';
    }
}
