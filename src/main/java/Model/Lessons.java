package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

public class Lessons {

    private String lessonName;

    private String exerciseName;

    private Exercise_Type type;

    private int maxPoint;

    public Lessons(String lessonName, String exerciseName, Exercise_Type type, int maxPoint) {
        this.lessonName = lessonName;
        this.exerciseName = exerciseName;
        this.type = type;
        this.maxPoint = maxPoint;
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

    public int getMaxPoint() {
        return maxPoint;
    }

    @Override
    public String toString() {
        return "Lessons{" +
                "lessonName='" + lessonName + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", type=" + type +
                ", maxPoint=" + maxPoint +
                '}';
    }
}
