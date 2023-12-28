package Model;

import java.util.ArrayList;
import java.util.List;

public class Studentinfo {
    private String name;
    private String group;
    private int allActScores;
    private int allPractScores;
    private int allHwScores;
    private int allSemScores;
    private int allPoint;
    private int friendCount;
    public Studentinfo(String name, String group,int allActScores, int allHwScores, int allSemScores, int allPractScores,int friendCount){
        this.name=name;
        this.group=group;
        this.allPractScores=allPractScores;
        this.allHwScores=allHwScores;
        this.allActScores=allActScores;
        this.allSemScores=allSemScores;
        allPoint=allActScores+allHwScores+allSemScores+allPractScores;
        this.friendCount=friendCount;
    }
    public int getAllPoint(){return allPoint;}
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
    public int getFriendCount(){return friendCount;}
    @Override
    public String toString() {
        return String.format("%s %s\tвсего за курс %d:За активность: %d\t За семенары: %d\t За упражнения: %d\t За домашки: %d",name,group,this.getAllPoint(),allActScores,allSemScores,allPractScores,allHwScores);
    }
}
