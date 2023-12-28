package Model;

import java.util.ArrayList;
import java.util.List;

public class StudentAllInfo {
    private String name;
    private String group;
    private List<Lesson> lessons= new ArrayList<>();
    private int allActScores;
    private int allPractScores;
    private int allHwScores;
    private int allSemScores;
    private int allPoint;
    private Integer friendCount=null;
    public StudentAllInfo(String name, String group, List<Lesson> lessons, int allActScores, int allHwScores, int allSemScores, int allPractScores){
        this.name=name;
        this.group=group;
        this.lessons=lessons;
        this.allPractScores=allPractScores;
        this.allHwScores=allHwScores;
        this.allActScores=allActScores;
        this.allSemScores=allSemScores;
        allPoint=allActScores+allHwScores+allSemScores+allPractScores;
    }
    public int getAllPoint(){return allPoint;}
    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public List<Lesson> getLessons() {
        return lessons;
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
    public Integer getFriendCount(){return friendCount;}
    public void setFriendCount(Integer count){
        friendCount=count;
    }
    @Override
    public String toString() {
        return String.format("%s %s\tвсего за курс %d:За активность: %d\t За семенары: %d\t За упражнения: %d\t За домашки: %d\tКоличество друзей в вк: %d",name,group,this.getAllPoint(),allActScores,allSemScores,allPractScores,allHwScores,friendCount);
    }
}
