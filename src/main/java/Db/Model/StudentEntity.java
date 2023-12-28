package Db.Model;

import Model.Lesson;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.SerializableType;
import com.j256.ormlite.table.DatabaseTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DatabaseTable(tableName = "students")
public class StudentEntity {
    public static final String NAME_COLUMN = "name";
    @DatabaseField(generatedId = true)
    private long studentId;
    @DatabaseField(canBeNull = false)
    private String name;
    @DatabaseField(canBeNull = false)
    private String group;
    @DatabaseField(canBeNull = false)
    private int allActScores;
    @DatabaseField(canBeNull = false)
    private int allPractScores;
    @DatabaseField(canBeNull = false)
    private int allHwScores;
    @DatabaseField(canBeNull = false)
    private int allSemScores;
    @DatabaseField(canBeNull = false)
    private int allPoint;
    @DatabaseField()
    private Integer friendCount;

    public StudentEntity() {}

    public StudentEntity(String name, String group, int allActScores, int allHwScores, int allSemScores, int allPractScores, int allPoint, Integer friendCount) {
        this.name = name;
        this.group = group;
        this.allPractScores = allPractScores;
        this.allHwScores = allHwScores;
        this.allActScores = allActScores;
        this.allSemScores = allSemScores;
        this.friendCount = friendCount;
        this.allPoint = allPoint;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getAllActScores() {
        return allActScores;
    }

    public int getAllPractScores() {
        return allPractScores;
    }

    public int getAllHwScores() {
        return allHwScores;
    }

    public int getAllSemScores() {
        return allSemScores;
    }

    public int getAllPoint() {
        return allPoint;
    }

    public Integer getFriendCount() {
        return friendCount;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", allActScores=" + allActScores +
                ", allPractScores=" + allPractScores +
                ", allHwScores=" + allHwScores +
                ", allSemScores=" + allSemScores +
                ", allPoint=" + allPoint +
                ", friendCount=" + friendCount +
                '}';
    }
}
