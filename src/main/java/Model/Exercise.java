package Model;

import Model.Exercise_Type;

public class Exercise {
    private Exercise_Type type;
    private String name;
    private  int maxScore;
    private int pointsScored;
    public Exercise(Exercise_Type type, String name, int maxScore, int pointsScored){
        this.type=type;
        this.name=name;
        this.maxScore=maxScore;
        this.pointsScored=pointsScored;
    }
    public Exercise_Type getType() {return type;}

    public String getName() {return name;}

    public int getMaxScore() {return maxScore;}

    public int getPointsScored() {return pointsScored;}
}
