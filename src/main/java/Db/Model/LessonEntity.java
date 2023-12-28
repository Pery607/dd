package Db.Model;

import Model.Exercise_Type;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

@DatabaseTable(tableName = "lessons")
public class LessonEntity {
    public static final String NAME_COLUMN = "lessonName";
    @DatabaseField(generatedId = true)
    private long lessonId;
    @DatabaseField(canBeNull = false)
    private String lessonName;
    @DatabaseField(canBeNull = false)
    private String exerciseName;
    @DatabaseField(canBeNull = false)
    private Exercise_Type type;
    @DatabaseField(canBeNull = false)
    private int maxPoint;

    public LessonEntity() {
    }

    public LessonEntity(String lessonName, String exerciseName, Exercise_Type type, int maxPoint) {
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
        return "LessonEntity{" +
                "lessonId=" + lessonId +
                ", lessonName='" + lessonName + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", type=" + type +
                ", maxPoint=" + maxPoint +
                '}';
    }
}
